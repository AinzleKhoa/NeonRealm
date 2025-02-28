/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.time.LocalDate;
import java.util.List;

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
    private List<Integer> categoryIds; // Store multiple category IDs
    private List<Integer> platformIds; // Store multiple category IDs

    public Game() {
    }

    public Game(int gameId, String title, String description, String imageUrl, double price, LocalDate releaseDate, LocalDate createdAt, List<Integer> categoryIds, List<Integer> platformIds) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.categoryIds = categoryIds;
        this.platformIds = platformIds;
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

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<Integer> getPlatformIds() {
        return platformIds;
    }

    public void setPlatformIds(List<Integer> platformIds) {
        this.platformIds = platformIds;
    }

}
