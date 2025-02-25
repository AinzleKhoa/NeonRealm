/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class GameDAO extends DBContext {

    // Retrieve all games
    public ArrayList<Game> getList() {
        ArrayList<Game> games = new ArrayList<>();
        String query = "";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("gameId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getDouble("price"),
                        rs.getDate("releaseDate").toLocalDate(),
                        rs.getDate("createAt").toLocalDate(),
                        rs.getInt("categoryId"),
                        rs.getInt("platformId")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return games;
    }
}
