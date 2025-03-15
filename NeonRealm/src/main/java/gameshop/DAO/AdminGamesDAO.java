/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminGames;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminGamesDAO extends DBContext {

    /**
     *
     * @param searchQuery
     * @param selectedGenre
     * @param offset
     * @param totalGamesPerPage
     * @return
     */
    public List<AdminGames> getGamesByFilter(String searchQuery, String selectedGenre, int offset, int totalGamesPerPage) {
        List<AdminGames> games = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT DISTINCT g.game_id AS id, \n"
                + "    g.title, g.description, g.release_date, g.created_at, g.image_url, g.price, \n"
                + "    -- Aggregating Developers\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT d.name \n"
                + "     FROM Game_Developers gd \n"
                + "     JOIN Developers d ON d.developer_id = gd.developer_id \n"
                + "     WHERE gd.game_id = g.game_id) AS unique_developers), '') AS developers,\n"
                + "    -- Aggregating Publishers\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT p.name \n"
                + "     FROM Game_Publishers gp \n"
                + "     JOIN Publishers p ON p.publisher_id = gp.publisher_id \n"
                + "     WHERE gp.game_id = g.game_id) AS unique_publishers), '') AS publishers,\n"
                + "    -- Aggregating Genres\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT gs.name \n"
                + "     FROM Game_Genres gg \n"
                + "     JOIN Genres gs ON gs.genre_id = gg.genre_id \n"
                + "     WHERE gg.game_id = g.game_id) AS unique_genres), '') AS genres,\n"
                + "    -- Aggregating Platforms\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT pl.name \n"
                + "     FROM Game_Platforms gp \n"
                + "     JOIN Platforms pl ON pl.platform_id = gp.platform_id \n"
                + "     WHERE gp.game_id = g.game_id) AS unique_platforms), '') AS platforms,\n"
                + "    -- Aggregating Categories\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT c.name \n"
                + "     FROM Game_Categories gc \n"
                + "     JOIN Categories c ON c.category_id = gc.category_id \n"
                + "     WHERE gc.game_id = g.game_id) AS unique_categories), '') AS categories\n"
                + "FROM Games g\n"
                + "LEFT JOIN Game_Genres gg ON g.game_id = gg.game_id\n"
                + "LEFT JOIN Genres gs ON gg.genre_id = gs.genre_id\n"
                + "WHERE 1=1 ");

        // Nếu có tìm kiếm, thêm điều kiện
        List<Object> parameters = new ArrayList<>();
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query.append(" AND g.title LIKE ? ");
            parameters.add("%" + searchQuery.trim() + "%");
        }

        // Nếu có lọc theo thể loại
        if (selectedGenre != null && !selectedGenre.trim().isEmpty()) {
            query.append(" AND gs.name = ? ");
            parameters.add(selectedGenre.trim());
        }

        query.append(" ORDER BY g.game_id ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
        parameters.add(offset);
        parameters.add(totalGamesPerPage);

        try ( PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    games.add(new AdminGames(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image_url"),
                            rs.getBigDecimal("price"),
                            rs.getDate("release_date"),
                            rs.getTimestamp("created_at"),
                            splitStringToList(rs.getString("developers")),
                            splitStringToList(rs.getString("publishers")),
                            splitStringToList(rs.getString("genres")),
                            splitStringToList(rs.getString("platforms")),
                            splitStringToList(rs.getString("categories"))
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }

    /**
     * Utility method to safely convert a comma-separated string to a list.
     */
    private List<String> splitStringToList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu null hoặc trống
        }
        List<String> result = new ArrayList<>();
        for (String item : input.split("\\s*,\\s*")) { // Loại bỏ khoảng trắng xung quanh dấu phẩy
            result.add(item.trim());
        }
        return result;
    }

    // Thêm một game mới
    /**
     *
     * @param game
     * @return
     */
    public boolean addGame(AdminGames game) {
        String insertGameQuery = "INSERT INTO Games (title, description, image_url, price, release_date) VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = getConnection().prepareStatement(insertGameQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getDescription());
            ps.setString(3, game.getImageUrl());
            ps.setBigDecimal(4, game.getPrice());
            ps.setDate(5, new java.sql.Date(game.getReleaseDate().getTime()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            // Lấy game_id vừa chèn vào
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int gameId = generatedKeys.getInt(1);

                // Chèn các giá trị vào bảng quan hệ
                insertManyToMany("Game_Genres", "game_id", "genre_id", gameId, game.getGenres());
                insertManyToMany("Game_Categories", "game_id", "category_id", gameId, game.getCategories());
                insertManyToMany("Game_Developers", "game_id", "developer_id", gameId, game.getDevelopers());
                insertManyToMany("Game_Publishers", "game_id", "publisher_id", gameId, game.getPublishers());
                insertManyToMany("Game_Platforms", "game_id", "platform_id", gameId, game.getPlatforms());

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Phương thức để chèn dữ liệu vào bảng quan hệ nhiều-nhiều
     */
    private void insertManyToMany(String tableName, String gameColumn, String relatedColumn, int gameId, List<String> values) throws SQLException {
        if (values == null || values.isEmpty()) {
            return;
        }

        String query = "INSERT INTO " + tableName + " (" + gameColumn + ", " + relatedColumn + ") "
                + "SELECT ?, " + relatedColumn + " FROM " + tableName.replace("Game_", "")
                + " WHERE name = ?";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            for (String value : values) {
                ps.setInt(1, gameId);
                ps.setString(2, value);
                ps.executeUpdate();
            }
        }
    }

    /**
     *
     * @param gameId
     * @return
     */
    public AdminGames getGameById(int gameId) {
        String query = "SELECT \n"
                + "    g.game_id AS id, \n"
                + "    g.title, \n"
                + "    g.description,\n"
                + "    g.release_date,\n"
                + "    g.created_at,\n"
                + "    g.image_url, \n"
                + "    g.price, \n"
                + "    -- Aggregating Developers\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT d.name \n"
                + "     FROM Game_Developers gd \n"
                + "     JOIN Developers d ON d.developer_id = gd.developer_id \n"
                + "     WHERE gd.game_id = g.game_id) AS unique_developers), '') AS developers,\n"
                + "    -- Aggregating Publishers\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT p.name \n"
                + "     FROM Game_Publishers gp \n"
                + "     JOIN Publishers p ON p.publisher_id = gp.publisher_id \n"
                + "     WHERE gp.game_id = g.game_id) AS unique_publishers), '') AS publishers,\n"
                + "    -- Aggregating Genres\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT gs.name \n"
                + "     FROM Game_Genres gg \n"
                + "     JOIN Genres gs ON gs.genre_id = gg.genre_id \n"
                + "     WHERE gg.game_id = g.game_id) AS unique_genres), '') AS genres,\n"
                + "    -- Aggregating Platforms\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT pl.name \n"
                + "     FROM Game_Platforms gp \n"
                + "     JOIN Platforms pl ON pl.platform_id = gp.platform_id \n"
                + "     WHERE gp.game_id = g.game_id) AS unique_platforms), '') AS platforms,\n"
                + "    -- Aggregating Categories\n"
                + "    COALESCE((SELECT STRING_AGG(name, ', ') FROM (SELECT DISTINCT c.name \n"
                + "     FROM Game_Categories gc \n"
                + "     JOIN Categories c ON c.category_id = gc.category_id \n"
                + "     WHERE gc.game_id = g.game_id) AS unique_categories), '') AS categories\n"
                + "FROM Games g \n"
                + "WHERE g.game_id = ?;";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, gameId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AdminGames(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        rs.getDate("release_date"),
                        rs.getTimestamp("created_at"),
                        splitStringToList(rs.getString("developers")),
                        splitStringToList(rs.getString("publishers")),
                        splitStringToList(rs.getString("genres")),
                        splitStringToList(rs.getString("platforms")),
                        splitStringToList(rs.getString("categories"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Xóa game theo ID
    /**
     *
     * @param gameId
     * @return
     */
    public boolean deleteGame(int gameId) {
        String query = "DELETE FROM Games WHERE game_id = ?";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, gameId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<String> getAllGenres() {
        return getListFromDB("SELECT name FROM genres");
    }

    public List<String> getAllCategories() {
        return getListFromDB("SELECT name FROM categories");
    }

    public List<String> getAllDevelopers() {
        return getListFromDB("SELECT name FROM developers");
    }

    public List<String> getAllPublishers() {
        return getListFromDB("SELECT name FROM publishers");
    }

    public List<String> getAllPlatforms() {
        return getListFromDB("SELECT name FROM platforms");
    }

// Hàm dùng chung để lấy danh sách chuỗi từ DB
    private List<String> getListFromDB(String query) {
        List<String> list = new ArrayList<>();
        try ( PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//update game ------------------------------------------------------------
    public boolean updateGame(AdminGames game) {
        String query = "UPDATE games SET title = ?, description = ?, image_url = ?, price = ?, release_date = ? WHERE game_id = ?";

        try ( PreparedStatement stmt = getConnection().prepareStatement(query)) {

            stmt.setString(1, game.getTitle());
            stmt.setString(2, game.getDescription());
            stmt.setString(3, game.getImageUrl()); // Có thể giữ nguyên ảnh cũ
            stmt.setBigDecimal(4, game.getPrice());
            stmt.setDate(5, game.getReleaseDate());
            stmt.setInt(6, game.getGameId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                updateGameRelations(conn, game);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateGameRelations(Connection conn, AdminGames game) throws SQLException {
        deleteGameRelations(conn, game.getGameId());
        insertGameRelations(conn, game.getGameId(), "game_genres", "genre_id", "Genres", "name", game.getGenres());
        insertGameRelations(conn, game.getGameId(), "game_categories", "category_id", "Categories", "name", game.getCategories());
        insertGameRelations(conn, game.getGameId(), "game_developers", "developer_id", "Developers", "name", game.getDevelopers());
        insertGameRelations(conn, game.getGameId(), "game_publishers", "publisher_id", "Publishers", "name", game.getPublishers());
        insertGameRelations(conn, game.getGameId(), "game_platforms", "platform_id", "Platforms", "name", game.getPlatforms());
    }

    private void deleteGameRelations(Connection conn, int gameId) throws SQLException {
        String[] tables = {"game_genres", "game_categories", "game_developers", "game_publishers", "game_platforms"};

        for (String table : tables) {
            String sql = "DELETE FROM " + table + " WHERE game_id = ?";
            try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, gameId);
                stmt.executeUpdate();
            }
        }
    }

    private void insertGameRelations(Connection conn, int gameId, String tableName, String idColumn, String refTable, String nameColumn, List<String> values) throws SQLException {
        if (values == null || values.isEmpty()) {
            return;
        }

        String query = "INSERT INTO " + tableName + " (game_id, " + idColumn + ") "
                + "SELECT ?, " + idColumn + " FROM " + refTable + " WHERE " + nameColumn + " = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            for (String value : values) {
                stmt.setInt(1, gameId);
                stmt.setString(2, value);
                stmt.executeUpdate();
            }
        }
    }

    /**
     *
     * @param searchQuery
     * @param selectedGenre
     * @return
     */
    public int countGamesByFilter(String searchQuerys, String selectedGenre) {
        StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT g.game_id) FROM Games g ");
        query.append("LEFT JOIN Game_Genres gg ON g.game_id = gg.game_id ");
        query.append("LEFT JOIN Genres gs ON gg.genre_id = gs.genre_id WHERE 1=1 ");

        String searchQuery = (searchQuerys != null) ? searchQuerys.trim() : "";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.append(" AND g.title LIKE ? ");
        }
        if (selectedGenre != null && !selectedGenre.isEmpty()) {
            query.append(" AND gs.name = ? ");
        }

        try ( PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (searchQuery != null && !searchQuery.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchQuery + "%");
            }
            if (selectedGenre != null && !selectedGenre.isEmpty()) {
                ps.setString(paramIndex++, selectedGenre);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
