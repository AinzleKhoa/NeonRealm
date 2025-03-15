/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.sql.Timestamp;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminUser {

    private int user_id;
    private String username;
    private String email;
    private String githubId;
    private String auth_provider; // "local" or "github"
    private String role;
    private String status;
    private String avatarUrl;
    private Timestamp last_login;
    private Timestamp created_at;

    public AdminUser(int user_id, String username, String email, String role, String status, Timestamp last_login, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.last_login = last_login;
        this.created_at = created_at;
    }

    public AdminUser(int user_id, String username, String email, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
    }

    public AdminUser(int user_id, String username, String email, String githubId, String authProvider, String avatarUrl, Timestamp last_login, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.githubId = githubId;
        this.auth_provider = authProvider;
        this.avatarUrl = avatarUrl;
        this.last_login = last_login;
        this.created_at = created_at;
    }

    public AdminUser() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getAuth_provider() {
        return auth_provider;
    }

    public void setAuth_provider(String auth_provider) {
        this.auth_provider = auth_provider;
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

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    
    
}
