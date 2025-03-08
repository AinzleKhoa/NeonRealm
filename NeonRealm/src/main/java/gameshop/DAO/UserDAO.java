/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.User;
import gameshop.util.PasswordUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ainzle
 */
public class UserDAO extends DBContext {

    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(user_id) FROM Users WHERE username = ?;";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{username})) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(user_id) FROM Users WHERE email = ?;";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{email})) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int signup(User user) {
        try {
            String query = "INSERT INTO Users (username, email, password_hash) VALUES\n"
                    + "(?,?,?);";
            Object[] params = {
                user.getUsername(),
                user.getEmail(),
                user.getHashedPassword(),};
            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public User login(String email, String password) {
        try {
            String query = "select *\n"
                    + "FROM Users u\n"
                    + "WHERE u.email = ?;"; // Password will be handled by Java util class not SQL query
            Object[] params = {email};
            ResultSet rs = execSelectQuery(query, params);
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");

                Instant lastLogin = (rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toInstant() : null);
                Instant createdAt = (rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toInstant() : null);

                // If the user is using local authentication, check the password
                if ("local".equals(rs.getString("auth_provider"))) {
                    if (hashedPassword == null || hashedPassword.isEmpty() || !PasswordUtils.checkPassword(password, hashedPassword)) { // PasswordUtils class in util package will handle it
                        return null; // Wrong password, stop executing immediately
                    }
                }

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("google_id"),
                        rs.getString("auth_provider"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getString("avatar_url"),
                        lastLogin,
                        createdAt
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
