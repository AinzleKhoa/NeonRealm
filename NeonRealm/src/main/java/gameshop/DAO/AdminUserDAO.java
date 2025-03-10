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
    // Lấy danh sách tất cả người dùng
    public List<AdminUser> getAllAdminUsers() {
        List<AdminUser> users = new ArrayList<>();
        String query = "SELECT user_id, username, email, full_name, phone, created_at FROM Users";
        
        try (ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                users.add(new AdminUser(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    // Xóa một mã giảm giá theo ID
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM Users WHERE user_id = ?";
        
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
