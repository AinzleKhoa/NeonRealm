/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

/**
 *
 * @author Ainzle
 */
public class User {

    private int userId;
    private String username;
    private String email;
    private String hashedPassword;
    private String fullName;
    private String phone;
    
    // For login return full details account
    public User(int userId, String username, String email, String hashedPassword, String fullName, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.fullName = fullName;
        this.phone = phone;
    }

    // For signup
    public User(String username, String email, String hashedPassword, String fullName, String phone) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.fullName = fullName;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
