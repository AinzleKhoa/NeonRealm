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
    public static void main(String[] a) {
        GameDAO gDAO = new GameDAO();
        ArrayList<Game> gs = gDAO.getList();
        for (Game g : gs) {
            System.out.println(g.getTitle());
        }
    }

    public ArrayList<Game> getList() {
        ArrayList<Game> games = new ArrayList<>();
        String query = "select game_id, title, description,image_url,price,release_date,created_at,category_id,platform_id from games";

        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getDouble("price"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getInt("category_id"),
                        rs.getInt("platform_id")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Err");
        }

        return games;
    }

    public Game getOneById(int id) {
        String query = "SELECT game_id, title, description, image_url, price, release_date, create_at, category_id, platform_id FROM games WHERE game_id = ?";
        Object[] params = {id};

        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {
                return new Game(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getDouble("price"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getDate("create_at").toLocalDate(),
                        rs.getInt("category_id"),
                        rs.getInt("platform_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Err");
        }

        return null;
    }

}
