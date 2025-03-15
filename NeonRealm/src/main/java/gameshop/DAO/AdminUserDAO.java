/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminUser;
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
public class AdminUserDAO extends DBContext {
    
    // Lấy danh sách người dùng với tìm kiếm & phân trang
    public List<AdminUser> getAllAdminUsers(String searchQuery, int offset, int limit) {
    List<AdminUser> users = new ArrayList<>();
    StringBuilder query = new StringBuilder(
        "SELECT user_id, username, email, role, status, last_login, created_at FROM Users WHERE 1=1"
    );

    // Nếu có tìm kiếm theo username
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
        query.append(" AND username LIKE ?");
    }

    query.append(" ORDER BY user_id ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

    try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
        int paramIndex = 1;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            ps.setString(paramIndex++, "%" + searchQuery.trim() + "%");
        }

        ps.setInt(paramIndex++, offset);
        ps.setInt(paramIndex++, limit);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            users.add(new AdminUser(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getString("status"),
                rs.getTimestamp("last_login"),
                rs.getTimestamp("created_at")
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return users;
}


    // Đếm số lượng user theo từ khóa tìm kiếm
    public int countUsersByFilter(String searchQuery) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Users WHERE 1=1");

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query.append(" AND username LIKE ?");
        }

        try ( PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                ps.setString(1, "%" + searchQuery.trim() + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    
    // Xóa một mã giảm giá theo ID
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM Users WHERE user_id = ?";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
