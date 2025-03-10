/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminGames {
    
    private int gameId;
    private String title;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Date releaseDate;
    private Timestamp createdAt;
    private List<String> developers;
    private List<String> publishers;
    private List<String> genres;
    private List<String> platforms;
    private List<String> categories;

    public AdminGames() {
    }

    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, Timestamp createdAt, List<String> developers, List<String> publishers, List<String> genres, List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.developers = developers;
        this.publishers = publishers;
        this.genres = genres;
        this.platforms = platforms;
        this.categories = categories;
    }
    
    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, List<String> developers, List<String> publishers, List<String> genres, List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.developers = developers;
        this.publishers = publishers;
        this.genres = genres;
        this.platforms = platforms;
        this.categories = categories;
    }


    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, Timestamp createdAt, List<String> genres, List<String> publishers) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.genres = genres;
        this.publishers = publishers;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    
    
}
