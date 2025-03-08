/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminGames;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * Retrieve all games from the database.
     * @return List of AdminGame objects
     */
    public List<AdminGames> getAllGames() {
        List<AdminGames> games = new ArrayList<>();
        String query = "SELECT game_id, title, description, image_url, price, release_date, created_at FROM Games";
        
        try (ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                games.add(new AdminGames(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        rs.getDate("release_date"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminGamesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }
}
