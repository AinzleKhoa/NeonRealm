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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ainzle
 */
public class UserDAO extends DBContext {

    public boolean isUsernameExists(String username) {
        try {
            String query = "SELECT count(u.user_id)\n"
                    + "FROM Users u\n"
                    + "WHERE u.username = ?;";
            Object[] params = {username};
            ResultSet rs = execSelectQuery(query, params);
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneExists(String phone) {
        try {
            String query = "SELECT count(u.user_id)\n"
                    + "FROM Users u\n"
                    + "WHERE u.phone = ?;";
            Object[] params = {phone};
            ResultSet rs = execSelectQuery(query, params);
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int signup(User user) {
        try {
            if (!isUsernameExists(user.getUsername()) || !isPhoneExists(user.getPhone())) {
                String query = "INSERT INTO Users (username, email, password_hash, full_name, phone) VALUES\n"
                        + "(?,?,?,?,?);";
                Object[] params = {
                    user.getUsername(),
                    user.getEmail(),
                    user.getHashedPassword(),
                    user.getFullName(),
                    user.getPhone()
                };
                return execQuery(query, params);
            } else {
                return 0;
            }
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

                if (PasswordUtils.checkPassword(password, hashedPassword)) { // PasswordUtils class in util package will handle it
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
