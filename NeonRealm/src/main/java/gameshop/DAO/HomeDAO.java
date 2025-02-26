/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String query = "select * from Games";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getDouble("price"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getDate("create_at").toLocalDate(),
                        rs.getInt("category_id"),
                        rs.getInt("platform_id")
                ));
            }
            System.out.println("Games loaded: " + games.size()); // Debug log
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return games;
    }
}
