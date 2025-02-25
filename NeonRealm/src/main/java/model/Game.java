/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class Game {
    private int gameId;
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private LocalDate releaseDate;
    private LocalDate createdAt;
    private int categoryId;
    private int platformId;
    
    public Game() {}

    public Game(int gameId, String title, String description, String imageUrl, double price, LocalDate releaseDate, LocalDate createdAt, int categoryId, int platformId) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.platformId = platformId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
    
    
}
