/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminOrders;
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
public class AdminOrdersDAO extends DBContext {
    // Lấy danh sách tất cả đơn hàng
    public List<AdminOrders> getAllOrders() {
    List<AdminOrders> orders = new ArrayList<>();
    String query = "SELECT o.order_id, o.user_id, u.username, o.total_price, o.discount_code, o.created_at, g.game_id, g.title " +
                       "FROM Orders o " +
                       "JOIN Users u ON o.user_id = u.user_id " +
                       "JOIN Order_Details od ON o.order_id = od.order_id " +
                       "JOIN Games g ON od.game_id = g.game_id";

    try (ResultSet rs = execSelectQuery(query)) {
        while (rs.next()) {
            orders.add(new AdminOrders(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getBigDecimal("total_price"),
                    rs.getString("discount_code"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("game_id"),  // Lấy ID game
                    rs.getString("title")
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return orders;
}


    
}
