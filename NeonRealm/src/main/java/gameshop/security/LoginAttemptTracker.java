/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.security;

import gameshop.db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ainzle
 */
public class LoginAttemptTracker extends DBContext {

    private static final int LOCKOUT_DURATION = 15 * 60 * 1000; // Lock for 15 minutes

    public int trackFailedLoginAttempt(String email) {
        String querySelect = "SELECT failed_attempts,\n"
                + "		last_failed_attempt,\n"
                + "		locked_until\n"
                + "FROM Users u\n"
                + "WHERE email = ?;";
        try ( ResultSet rs = execSelectQuery(querySelect, new Object[]{email})) { // Query into that specific account so that we can retrieve correctly
            if (rs.next()) { // If that email exist (The email must already be existed if it's called, but whatever)

                if (rs.getInt("failed_attempts") >= 5) { // get failed_attempts at the correct row at the correct account
                    lockAccount(email);
                    return -1; // Return -1 if the account is locked
                }

                // Only increment if not locked
                String queryUpdate = "UPDATE Users\n"
                        + "SET failed_attempts = ?,\n"
                        + "	last_failed_attempt = ?"
                        + "WHERE email = ?;";
                execQuery(queryUpdate, new Object[]{ // execQuery and save result to return later
                    rs.getInt("failed_attempts") + 1,
                    new Timestamp(System.currentTimeMillis()),
                    email
                });

                return 0; // Indicate wrong password but not locked
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Default case: Incorrect login (not locked)
    }

    public int resetFailedAttempts(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET failed_attempts = 0\n"
                    + "WHERE email = ?;";
            Object[] params = {email};

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int lockAccount(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET locked_until = ?\n"
                    + "WHERE email = ?;";
            Object[] params = {
                new Timestamp(System.currentTimeMillis() + LOCKOUT_DURATION),
                email
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int saveLoginTime(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET last_login = ?\n"
                    + "WHERE email = ?;";
            Object[] params = {
                new Timestamp(System.currentTimeMillis()),
                email
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
