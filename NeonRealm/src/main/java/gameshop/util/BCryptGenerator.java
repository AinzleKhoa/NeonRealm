/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

import gameshop.DAO.UserDAO;
import gameshop.model.User;

/**
 *
 * @author Ainzle
 */
public class BCryptGenerator {

    public static void generateBCrypt() {
        String username = "ainzle";
        String email = "ainzle@gmail.com";
        String plainPassword = "123";
        String fullName = "AinzleKhoa";
        String phone = "0948481805";

        String hashedPassword = PasswordUtils.hashPassword(plainPassword);

        System.out.println("** Hashed Password: " + hashedPassword);

        UserDAO uDAO = new UserDAO();
        if (uDAO.signup(new User(username, email, hashedPassword, fullName, phone)) > 0) {
            System.out.println("✅ Generate a new account successfully!");
            System.out.println("🔹 Username: " + username);
            System.out.println("🔹 Email: " + email);
            System.out.println("🔹 Full Name: " + fullName);
            System.out.println("🔹 Phone: " + phone);
            System.out.println("🔹 Stored Hashed Password: " + hashedPassword);
        } else {
            System.out.println("❌ Fails to generate a new account X_X");
        }
    }
}
