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
    try {
        String query = "SELECT \n"
                + "    od.order_detail_id,\n"
                + "    o.order_id,\n"
                + "    o.user_id,\n"
                + "    u.username,\n"
                + "    o.discount_code,\n"
                + "    o.created_at,\n"
                + "    g.game_id,\n"
                + "    g.title,\n"
                + "    od.price\n"  // Lấy giá từ Order_Details
                + "FROM Order_Details od\n"
                + "JOIN Orders o ON od.order_id = o.order_id\n"
                + "JOIN Users u ON o.user_id = u.user_id\n"
                + "JOIN Games g ON od.game_id = g.game_id\n";

        // Kiểm tra điều kiện sắp xếp
        if ("asc".equals(sortPrice)) {
            query += "ORDER BY od.price ASC, o.created_at DESC\n";
        } else if ("desc".equals(sortPrice)) {
            query += "ORDER BY od.price DESC, o.created_at DESC\n";
        } else {
            query += "ORDER BY o.order_id ASC\n"; // Mặc định theo order_id tăng dần
        }

        // Thêm phân trang
        query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AdminOrders order = new AdminOrders(
                        rs.getInt("order_detail_id"),
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getBigDecimal("price"), // Giá lấy từ Order_Details
                        rs.getString("discount_code"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("game_id"),
                        rs.getString("title")
                );
                orders.add(order);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, "Error fetching all orders", ex);
    }
    return orders;
}





// Hàm đếm số đơn hàng theo bộ lọc
    public int countTotalOrders() {
        String query = "SELECT COUNT(*) FROM Order_Details";
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
