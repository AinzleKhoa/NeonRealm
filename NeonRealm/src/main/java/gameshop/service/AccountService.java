/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.service;

import gameshop.DAO.AccountDAO;
import gameshop.model.Account;

import java.time.LocalDateTime;

public class AccountService {
    private final AccountDAO accountDAO;

   public AccountService() {
        this.accountDAO = new AccountDAO(); // Đảm bảo AccountDAO được khởi tạo
    }
    // ✅ Lưu token đặt lại mật khẩu vào database
    public boolean savePasswordResetToken(String email, String token) {
        Account account = accountDAO.getAccountByEmail(email);
        if (account != null) {
            account.setResetToken(token);
            account.setTokenExpiry(LocalDateTime.now().plusMinutes(30)); // Token hết hạn sau 30 phút
            return accountDAO.updateAccount(account);
        }
        return false;
    }

    //  Kiểm tra token có hợp lệ không
    public boolean isValidResetToken(String token) {
        Account account = accountDAO.getAccountByToken(token);
        return account != null && account.getTokenExpiry() != null && account.getTokenExpiry().isAfter(LocalDateTime.now());
    }

   public boolean updatePasswordByToken(String token, String newPassword) {
    if (token == null || newPassword == null) {
        System.out.println("⚠️ Token hoặc mật khẩu mới bị null!");
        return false;
    }

    Account account = accountDAO.getAccountByToken(token);
    if (account == null) {
        System.out.println("⚠️ Không tìm thấy tài khoản với token: " + token);
        return false;
    }

    if (!isValidResetToken(token)) {
        System.out.println("❌ Token không hợp lệ hoặc đã hết hạn.");
        return false;
    }

    account.setPasswordHash(newPassword); // Mã hóa mật khẩu trước khi lưu
    account.setResetToken(null); // Xóa token sau khi đổi mật khẩu
    account.setTokenExpiry(null);
    
    return accountDAO.updateAccount(account);
}

}
