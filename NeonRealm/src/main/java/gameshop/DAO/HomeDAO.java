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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class HomeDAO extends DBContext {
    // Retrieve all games 

    public ArrayList<Game> getList() {
        ArrayList<Game> games = new ArrayList<>();
        String query = "SELECT g.game_id, g.title, g.description, g.image_url, g.price, g.release_date, g.created_at, "
                + "STRING_AGG(gc.category_id, ',') AS category_ids, "
                + "STRING_AGG(gp.platform_id, ',') AS platform_ids "
                + "FROM Games g "
                + "LEFT JOIN Game_Categories gc ON g.game_id = gc.game_id "
                + "LEFT JOIN Game_Platforms gp ON g.game_id = gp.game_id "
                + "GROUP BY g.game_id, g.title, g.description, g.image_url, g.price, g.release_date, g.created_at";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                LocalDate releaseDate = (rs.getDate("release_date") != null ? rs.getDate("release_date").toLocalDate() : null);
                LocalDate createdAt = (rs.getDate("created_at") != null ? rs.getDate("created_at").toLocalDate() : null);

                // Convert category_ids to List<Integer>
                List<Integer> categoryIds = new ArrayList<>();
                if (rs.getString("category_ids") != null) {
                    String[] ids = rs.getString("category_ids").split(","); // Split comma-separated IDs
                    for (String id : ids) {
                        try {
                            categoryIds.add(Integer.parseInt(id.trim())); // Convert to integer safely
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category ID: " + id); // Debugging log
                        }
                    }
                }

                List<Integer> platformIds = new ArrayList<>();
                if (rs.getString("platform_ids") != null) {
                    String[] ids = rs.getString("platform_ids").split(",");
                    for (String id : ids) {
                        try {
                            platformIds.add(Integer.parseInt(id.trim()));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform ID: " + id);
                        }
                    }
                }

                games.add(new Game(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getDouble("price"),
                        releaseDate,
                        createdAt,
                        categoryIds,
                        platformIds
                ));
            }
            System.out.println("Games loaded: " + games.size()); // Debug log
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return games;
    }
}
