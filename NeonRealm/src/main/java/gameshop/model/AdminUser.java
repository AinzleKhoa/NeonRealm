/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminUser {
    private int userId;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String googleAuthId;
    private Timestamp createdAt;

    public AdminUser(int userId, String username, String email, String fullName, String phone, String googleAuthId, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.googleAuthId = googleAuthId;
        this.createdAt = createdAt;
    }

    public AdminUser(int userId, String username, String email, String fullName, String phone, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.createdAt = createdAt;
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

    public String getGoogleAuthId() {
        return googleAuthId;
    }

    public void setGoogleAuthId(String googleAuthId) {
        this.googleAuthId = googleAuthId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
