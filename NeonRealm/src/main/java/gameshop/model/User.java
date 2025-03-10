/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.time.Instant;

/**
 *
 * @author Ainzle
 */
public class User {

    private int userId;
    private String username;
    private String email;
    private String hashedPassword;
    private String githubId;
    private String authProvider; // "local" or "github"
    private String role = "user";
    private String status = "active";
    private String avatarUrl;
    private Instant lastLogin;
    private Instant createdAt = Instant.now();

    // Constructors
    public User() {
    }

    // Constructor for debugging login issues.
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // For login locally
    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    // For signup locally and provider login/signup
    public User(String username, String email, String hashedPassword, String githubId, String authProvider) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.githubId = githubId;
        this.authProvider = authProvider;
    }

    // For login retrieving all data
    public User(int userId, String username, String email, String hashedPassword, String githubId, String authProvider, String role, String status, String avatarUrl, Instant lastLogin, Instant createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.githubId = githubId;
        this.authProvider = authProvider;
        this.role = role;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.lastLogin = lastLogin;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
