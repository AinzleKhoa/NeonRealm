/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Order;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALIENWARE
 */
public class OrderDetailsDAO extends DBContext {

    public static void main(String[] a) {
        OrderDetailsDAO oDAO = new OrderDetailsDAO();
        BigDecimal db = new BigDecimal(99);
        oDAO.addOrderDetails(21, 2, db);
    }

    public boolean addOrderDetails(int orderId, int gameId, BigDecimal price) {
        String query = "INSERT INTO Order_Details (order_id, game_id, price) VALUES (?, ?, ?)";
        try ( Connection conn = this.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, gameId);
            stmt.setBigDecimal(3, price);

            int rowsInserted = stmt.executeUpdate(); // Thực thi INSERT
            System.out.println("Rows affected: " + rowsInserted);

            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, "Error adding order detail", ex);
            return false;
        }
    }
}
