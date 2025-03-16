/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.User;
import gameshop.security.LoginAttemptTracker;
import gameshop.util.PasswordUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CE190449 - Le Anh Khoa
 */
public class UserDAO extends DBContext {

    /**
     * Retrieves a user by their GitHub ID.
     *
     * @param githubId the GitHub ID of the user.
     * @return the User object corresponding to the given GitHub ID, or null if
     * not found.
     */
    public User findByGitHubId(String githubId) {
        try {
            String query = "SELECT * FROM Users WHERE github_id = ?;";
            Object[] params = {githubId};
            ResultSet rs = execSelectQuery(query, params);
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("github_id"),
                        rs.getString("auth_provider"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getString("avatar_url"),
                        rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toInstant() : null,
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toInstant() : null
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the user ID.
     * @return the User object corresponding to the given ID, or null if not
     * found.
     */
    public User findById(int id) {
        try {
            String query = "SELECT * FROM Users WHERE user_id = ?;";
            Object[] params = {id};
            ResultSet rs = execSelectQuery(query, params);
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("github_id"),
                        rs.getString("auth_provider"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getString("avatar_url"),
                        rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toInstant() : null,
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toInstant() : null
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Checks if a username already exists in the database.
     *
     * @param username the username to check.
     * @return true if the username exists, false otherwise.
     */
    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(user_id) FROM Users WHERE username = ?;";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{username})) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Checks if an email already exists in the database.
     *
     * @param email the email to check.
     * @return true if the email exists, false otherwise.
     */
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(user_id) FROM Users WHERE email = ?;";
        try ( ResultSet rs = execSelectQuery(query, new Object[]{email})) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Updates the status of a user based on their email.
     *
     * @param status the new status to set.
     * @param email the email of the user to update.
     * @return the number of rows affected by the update.
     */
    public int setStatus(String status, String email) {
        try {
            String query = "UPDATE Users\n"
                    + "SET status = ?\n"
                    + "WHERE email = ?;";
            Object[] params = {
                status,
                email
            };
            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Signs up a new user by inserting their details into the database.
     *
     * @param user the User object containing the user's details.
     * @return the number of rows affected by the insert.
     */
    public int signup(User user) {
        try {
            String query = "INSERT INTO Users (username, email, password_hash, github_id, auth_provider, role, status, created_at) VALUES\n"
                    + "(?,?,?,?,?,?,?,?);";
            Timestamp timestamp = Timestamp.from(user.getCreatedAt()); // Convert Instant -> Timestamp
            Object[] params = {
                user.getUsername(), // CAN'T BE NULL
                user.getEmail(), // CAN'T BE NULL
                user.getHashedPassword(), // This will NOT be NULL for "local" users
                user.getGithubId(), // This will NOT be NULL for "github" users
                user.getAuthProvider(), // "local" or "github", will be inserted
                user.getRole(), // Default role: "user"
                user.getStatus(), // Default status: "active"
                timestamp
            };
            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // With new password
    /**
     * Updates a user's profile with a new username, email, and password.
     *
     * @param id the user ID.
     * @param username the new username.
     * @param email the new email.
     * @param newHashedPassword the new hashed password.
     * @return the number of rows affected by the update.
     */
    public int profileUpdate(int id, String username, String email, String newHashedPassword) {
        try {
            String query = "UPDATE Users\n"
                    + "SET username = ?, email = ?, password_hash = ?"
                    + "WHERE user_id = ?;";
            Object[] params = {
                username,
                email,
                newHashedPassword,
                id
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Updates a user's profile with a new username and email without changing
     * the password.
     *
     * @param id the user ID.
     * @param username the new username.
     * @param email the new email.
     * @return the number of rows affected by the update.
     */
    public int profileUpdate(int id, String username, String email) {
        try {
            String query = "UPDATE Users\n"
                    + "SET username = ?, email = ?"
                    + "WHERE user_id = ?;";
            Object[] params = {
                username,
                email,
                id
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Updates a user's authentication by verifying their old password.
     *
     * @param id the user ID.
     * @param oldPass the old password.
     * @return an integer indicating the result of the authentication update.
     */
    public int profileUpdateAuth(int id, String oldPass) {
        try {
            String query = "select *\n"
                    + "FROM Users u\n"
                    + "WHERE u.user_id = ?;";
            Object[] params = {id};
            ResultSet rs = execSelectQuery(query, params);

            // Step 1: Check if the id doesn't exist
            if (!rs.next()) {
                return -3; // Unexpected issues
            }

            LoginAttemptTracker tracker = new LoginAttemptTracker();
            String hashedPassword = rs.getString("password_hash");
            String email = rs.getString("email");

            // Step 2: check the password
            if (hashedPassword == null || hashedPassword.isEmpty() || !PasswordUtils.checkPassword(oldPass, hashedPassword)) { // PasswordUtils class in util package will handle it
                if (tracker.trackFailedLoginAttempt(email) == -1) { // If the password is incorrect and the account is locked
                    setStatus("locked", email);
                    return -1;
                } else { // If the account login attempt is less than 5 and is not locked. Only incorrect password
                    return -2;
                }
            }

            // If password is correct (The account isnt locked)
            tracker.resetFailedAttempts(email);
            tracker.saveLoginTime(email);
            setStatus("active", email);

            return 1; // Succeed
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Logs in a user by verifying their email and password.
     *
     * @param email the user's email.
     * @param password the user's password.
     * @return a User object representing the logged-in user or an error code.
     */
    public User login(String email, String password) {
        try {
            String query = "select *\n"
                    + "FROM Users u\n"
                    + "WHERE u.email = ?;"; // Password will be handled by Java util class not SQL query
            Object[] params = {email};
            ResultSet rs = execSelectQuery(query, params); // Take the cursor to that specific account row for rs get correctly later on

            // Step 1: Check if the email does exist
            if (!rs.next()) {
                return new User(-3, "EMAIL_NOT_FOUND");
            }

            // Step 2: Initialize login attempt tracker
            LoginAttemptTracker tracker = new LoginAttemptTracker();
            Timestamp lockedUntil = rs.getTimestamp("locked_until");

            // Step 3: Check if the account is locked, 
            if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                return new User(-1, "LOCKED"); // If account is locked
            }

            String hashedPassword = rs.getString("password_hash");

            // Step 4: check the password
            if (hashedPassword == null || hashedPassword.isEmpty() || !PasswordUtils.checkPassword(password, hashedPassword)) { // PasswordUtils class in util package will handle it
                if (tracker.trackFailedLoginAttempt(email) == -1) { // If the password is incorrect and the account is locked
                    setStatus("locked", email);
                    return new User(-1, "LOCKED");
                } else { // If the account login attempt is less than 5 and is not locked. Only incorrect password
                    return new User(-2, "INVALID_PASSWORD");
                }
            }

            // Step 5: If password is correct (The account isnt locked)
            tracker.resetFailedAttempts(email);
            tracker.saveLoginTime(email);
            setStatus("active", email);

            Instant lastLogin = (rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toInstant() : null);
            Instant createdAt = (rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toInstant() : null);

            return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("github_id"),
                    rs.getString("auth_provider"),
                    rs.getString("role"),
                    rs.getString("status"),
                    rs.getString("avatar_url"),
                    lastLogin,
                    createdAt
            );
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
