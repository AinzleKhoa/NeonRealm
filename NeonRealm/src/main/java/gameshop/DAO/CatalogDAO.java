/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ainzle
 */
public class CatalogDAO extends DBContext {

    public ArrayList getList() {
        ArrayList<Game> gameList = new ArrayList<>();
        String query = "SELECT \n"
                + "    g.game_id AS id, \n"
                + "    g.title, \n"
                + "	g.description,\n"
                + "	g.release_date,\n"
                + "    g.image_url, \n"
                + "    g.price, \n"
                + "	-- Subquery to remove duplicates before aggregation (Developers)\n"
                + "	(SELECT STRING_AGG(unique_developers.name, ', ')\n"
                + "	FROM (SELECT DISTINCT d.name\n"
                + "		  FROM Game_Developers gd\n"
                + "		  JOIN Developers d ON d.developer_id = gd.developer_id\n"
                + "		  WHERE g.game_id = gd.game_id) AS unique_developers\n"
                + "	) AS developers,\n"
                + "	-- Subquery to remove duplicates before aggregation (Publishers)\n"
                + "	(SELECT STRING_AGG(unique_publishers.name, ', ')\n"
                + "	FROM (SELECT DISTINCT p.name\n"
                + "		  FROM Game_Publishers gp\n"
                + "		  JOIN Publishers p ON p.publisher_id = gp.publisher_id\n"
                + "		  WHERE g.game_id = gp.game_id) AS unique_publishers\n"
                + "	) AS publishers,\n"
                + "	-- Subquery to remove duplicates before aggregation (Genres)\n"
                + "	(SELECT STRING_AGG(unique_genres.name, ', ')\n"
                + "	FROM (SELECT DISTINCT gs.name\n"
                + "		  FROM Game_Genres gg\n"
                + "		  JOIN Genres gs ON gs.genre_id = gg.genre_id\n"
                + "		  WHERE g.game_id = gg.game_id) AS unique_genres\n"
                + "	) AS genres,\n"
                + "    -- Subquery to remove duplicates before aggregation (Platforms)\n"
                + "    (SELECT STRING_AGG(unique_platforms.name, ', ') \n"
                + "     FROM (SELECT DISTINCT p.name \n"
                + "           FROM Game_Platforms gp \n"
                + "           JOIN Platforms p ON p.platform_id = gp.platform_id \n"
                + "           WHERE gp.game_id = g.game_id) AS unique_platforms\n"
                + "    ) AS platforms,\n"
                + "    -- Subquery to remove duplicates before aggregation (Categories)\n"
                + "    (SELECT STRING_AGG(unique_categories.name, ', ') \n"
                + "     FROM (SELECT DISTINCT c.name \n"
                + "           FROM Game_Categories gc \n"
                + "           JOIN Categories c ON c.category_id = gc.category_id \n"
                + "           WHERE gc.game_id = g.game_id) AS unique_categories\n"
                + "    ) AS categories\n"
                + "FROM Games g;";
        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {

                LocalDate releaseDate = (rs.getDate("release_date") != null ? rs.getDate("release_date").toLocalDate() : null);

                List<String> developerNames = new ArrayList<>();
                if (rs.getString("developers") != null) {
                    String[] names = rs.getString("developers").split(", ");
                    for (String name : names) {
                        try {
                            developerNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid developers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> publisherNames = new ArrayList<>();
                if (rs.getString("publishers") != null) {
                    String[] names = rs.getString("publishers").split(", ");
                    for (String name : names) {
                        try {
                            publisherNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid publishers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> genreNames = new ArrayList<>();
                if (rs.getString("genres") != null) {
                    String[] names = rs.getString("genres").split(",");
                    for (String name : names) {
                        try {
                            genreNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid genres name: " + name); // Debugging log
                        }
                    }
                }

                List<String> platformNames = new ArrayList<>();
                if (rs.getString("platforms") != null) {
                    String[] names = rs.getString("platforms").split(",");
                    for (String name : names) {
                        try {
                            platformNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform name: " + name); // Debugging log
                        }
                    }
                }

                List<String> categoryNames = new ArrayList<>();
                if (rs.getString("categories") != null) {
                    String[] names = rs.getString("categories").split(",");
                    for (String name : names) {
                        try {
                            categoryNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category name: " + name); // Debugging log
                        }
                    }
                }

                gameList.add(new Game(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        releaseDate,
                        developerNames,
                        publisherNames,
                        genreNames,
                        platformNames,
                        categoryNames
                ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CatalogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameList;
    }
}
