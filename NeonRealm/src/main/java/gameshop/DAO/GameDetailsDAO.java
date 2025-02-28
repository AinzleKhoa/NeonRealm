/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ainzle
 */
public class GameDetailsDAO extends DBContext {

//    public Game getGameById(int id) {
//        Game game = null;
//        String queryGetId = "SELECT * FROM Games WHERE game_id = ?";
//        Object[] params = {id};
//        try ( ResultSet rs = execSelectQuery(queryGetId, params)) {
//            if (rs.next()) {
//                game = new Game(
//                        rs.getInt("game_id"),
//                        rs.getString("title"),
//                        rs.getString("description"),
//                        rs.getString("image_url"),
//                        rs.getDouble("price"),
//                        rs.getDate("release_date").toLocalDate(),
//                        rs.getDate("created_at").toLocalDate(),
//                        rs.getInt("category_id"),
//                        rs.getInt("platform_id")
//                );
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GameDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return game;
//    }
}
