/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminOrders;
import java.sql.PreparedStatement;
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
    public List<AdminOrders> getAllOrders(String sortPrice, int offset, int pageSize) {
        List<AdminOrders> orders = new ArrayList<>();
        String query = "SELECT o.order_id, o.user_id, u.username, o.total_price, o.discount_code, o.created_at, "
                + "g.game_id, g.title FROM Orders o "
                + "JOIN Users u ON o.user_id = u.user_id "
                + "JOIN Order_Details od ON o.order_id = od.order_id "
                + "JOIN Games g ON od.game_id = g.game_id ";

        // Mặc định sắp xếp theo order_id tăng dần
        if ("asc".equals(sortPrice)) {
            query += "ORDER BY o.total_price ASC ";
        } else if ("desc".equals(sortPrice)) {
            query += "ORDER BY o.total_price DESC ";
        } else {
            query += "ORDER BY o.order_id ASC "; // Mặc định theo order_id
        }

        // Thêm phân trang
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new AdminOrders(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("discount_code"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("game_id"),
                        rs.getString("title")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

// Hàm đếm số đơn hàng theo bộ lọc
    public int countTotalOrders() {
        String query = "SELECT COUNT(*) FROM Orders";
        try ( PreparedStatement ps = getConnection().prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Lấy tổng số đơn hàng
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Trả về 0 nếu có lỗi
    }

}
