/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminGames;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminGamesDAO extends DBContext {

    /**
     * Retrieve all games from the database, including genres and publishers.
     *
     * @return List of AdminGames objects
     */
    public List<AdminGames> getAllGames() {
        List<AdminGames> games = new ArrayList<>();
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
                + "FROM Games g;";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
//            LocalDate releaseDate = rs.getDate("release_date") != null ? rs.getDate("release_date").toLocalDate() : null;

                // Utility method to safely split and trim strings
                List<String> developerNames = splitStringToList(rs.getString("developers"));
                List<String> publisherNames = splitStringToList(rs.getString("publishers"));
                List<String> genreNames = splitStringToList(rs.getString("genres"));
                List<String> platformNames = splitStringToList(rs.getString("platforms"));
                List<String> categoryNames = splitStringToList(rs.getString("categories"));

                // Add game to list
                games.add(new AdminGames(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        rs.getDate("release_date"),
                        rs.getTimestamp("created_at"),
                        developerNames,
                        publisherNames,
                        genreNames,
                        platformNames,
                        categoryNames
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }


    public List<AdminGames> getGamesByGenre(String genre) {
        List<AdminGames> games = new ArrayList<>();
        String query = "SELECT g.game_id AS id, g.title, g.description, g.release_date, g.created_at, g.image_url, g.price, "
                + " COALESCE((SELECT STRING_AGG(gs.name, ', ') WITHIN GROUP (ORDER BY gs.name) "
                + " FROM Game_Genres gg JOIN Genres gs ON gs.genre_id = gg.genre_id WHERE gg.game_id = g.game_id), '') AS genres, "
                + " COALESCE((SELECT STRING_AGG(p.name, ', ') WITHIN GROUP (ORDER BY p.name) "
                + " FROM Game_Publishers gp JOIN Publishers p ON p.publisher_id = gp.publisher_id WHERE gp.game_id = g.game_id), '') AS publishers, "
                + " COALESCE((SELECT STRING_AGG(d.name, ', ') WITHIN GROUP (ORDER BY d.name) "
                + " FROM Game_Developers gd JOIN Developers d ON d.developer_id = gd.developer_id WHERE gd.game_id = g.game_id), '') AS developers, "
                + " COALESCE((SELECT STRING_AGG(pl.name, ', ') WITHIN GROUP (ORDER BY pl.name) "
                + " FROM Game_Platforms gp JOIN Platforms pl ON pl.platform_id = gp.platform_id WHERE gp.game_id = g.game_id), '') AS platforms, "
                + " COALESCE((SELECT STRING_AGG(c.name, ', ') WITHIN GROUP (ORDER BY c.name) "
                + " FROM Game_Categories gc JOIN Categories c ON c.category_id = gc.category_id WHERE gc.game_id = g.game_id), '') AS categories "
                + " FROM Games g "
                + " JOIN Game_Genres gg ON g.game_id = gg.game_id "
                + " JOIN Genres gs ON gg.genre_id = gs.genre_id "
                + " WHERE gs.name = ?";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, genre);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    // Utility method to safely split and trim strings
                    List<String> developerNames = splitStringToList(rs.getString("developers"));
                    List<String> publisherNames = splitStringToList(rs.getString("publishers"));
                    List<String> genreNames = splitStringToList(rs.getString("genres"));
                    List<String> platformNames = splitStringToList(rs.getString("platforms"));
                    List<String> categoryNames = splitStringToList(rs.getString("categories"));

                    games.add(new AdminGames(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image_url"),
                            rs.getBigDecimal("price"),
                            rs.getDate("release_date"),
                            rs.getTimestamp("created_at"),
                            developerNames,
                            publisherNames,
                            genreNames,
                            platformNames,
                            categoryNames
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
    public boolean addGame(AdminGames game) {
    String insertGameQuery = "INSERT INTO Games (title, description, image_url, price, release_date) VALUES (?, ?, ?, ?, ?)";
    
    try (PreparedStatement ps = getConnection().prepareStatement(insertGameQuery, Statement.RETURN_GENERATED_KEYS)) {
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
    if (values == null || values.isEmpty()) return;

    String query = "INSERT INTO " + tableName + " (" + gameColumn + ", " + relatedColumn + ") " +
                   "SELECT ?, " + relatedColumn + " FROM " + tableName.replace("Game_", "") + 
                   " WHERE name = ?";
    
    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
        for (String value : values) {
            ps.setInt(1, gameId);
            ps.setString(2, value);
            ps.executeUpdate();
        }
    }
}



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

    try (PreparedStatement ps = getConnection().prepareStatement(query)) {
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
    try (PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
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
        
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

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
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, gameId);
                stmt.executeUpdate();
            }
        }
    }

    private void insertGameRelations(Connection conn, int gameId, String tableName, String idColumn, String refTable, String nameColumn, List<String> values) throws SQLException {
    if (values == null || values.isEmpty()) return;

    String query = "INSERT INTO " + tableName + " (game_id, " + idColumn + ") " +
                   "SELECT ?, " + idColumn + " FROM " + refTable + " WHERE " + nameColumn + " = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        for (String value : values) {
            stmt.setInt(1, gameId);
            stmt.setString(2, value);
            stmt.executeUpdate();
        }
    }
}


}
