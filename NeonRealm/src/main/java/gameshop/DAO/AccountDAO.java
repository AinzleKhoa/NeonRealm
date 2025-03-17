/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;


import gameshop.db.DBContext;
import gameshop.model.Account;
import java.sql.*;
import java.time.LocalDateTime;

public class AccountDAO {
    private Connection conn;
    private DBContext dbContext;

    // Constructor - Nhận DBContext và sử dụng kết nối có sẵn
    public AccountDAO() {
        dbContext = new DBContext();
        conn = dbContext.getConnection(); // Lấy kết nối từ DBContext
    }
    // 🔍 Lấy tài khoản theo email
    public Account getAccountByEmail(String email) {
        try {
            String query = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔍 Lấy tài khoản theo token
    public Account getAccountByToken(String token) {
    if (token == null || token.trim().isEmpty()) {
        System.out.println("⚠️ Token bị null hoặc rỗng!");
        return null;
    }

    try {
        String query = "SELECT * FROM Users WHERE reset_token = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, token);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            System.out.println("⚠️ Không tìm thấy tài khoản với token: " + token);
            return null;
        }

        return mapResultSetToAccount(rs);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean updateResetToken(String email, String token, LocalDateTime expiryTime) {
    String query = "UPDATE Users SET reset_token = ?, token_expiry = ? WHERE email = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, token);
        stmt.setTimestamp(2, Timestamp.valueOf(expiryTime));
        stmt.setString(3, email);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Nếu cập nhật thành công, trả về true
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // 📝Cập nhật tài khoản (dùng cho update mật khẩu và token)
    public boolean updateAccount(Account account) {
        try {
            String query = "UPDATE Users SET password_hash = ?, reset_token = ?, token_expiry = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, account.getPasswordHash());
            stmt.setString(2, account.getResetToken());
            stmt.setTimestamp(3, account.getTokenExpiry() != null ? Timestamp.valueOf(account.getTokenExpiry()) : null);
            stmt.setString(4, account.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔄 Chuyển ResultSet thành Account object
    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setEmail(rs.getString("email"));
        account.setPasswordHash(rs.getString("password_hash"));
        account.setResetToken(rs.getString("reset_token"));
        account.setTokenExpiry(rs.getTimestamp("token_expiry") != null ? rs.getTimestamp("token_expiry").toLocalDateTime() : null);
        return account;
    }
}

