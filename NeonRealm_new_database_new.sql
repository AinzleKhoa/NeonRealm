-- Create the Database
CREATE DATABASE NeonRealm;
GO

-- Use the Database
USE NeonRealm;
GO

-- Create Users Table
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(255) UNIQUE NOT NULL,
    email NVARCHAR(255) UNIQUE NOT NULL,
    password_hash NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(255),
    phone NVARCHAR(20),
    google_auth_id NVARCHAR(255) NULL,
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create Developers Table
CREATE TABLE Developers (
    developer_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Publishers Table
CREATE TABLE Publishers (
    publisher_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Genres Table (For Game Genres)
CREATE TABLE Genres (
    genre_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Categories Table (For Store Categories - NOT Genres)
CREATE TABLE Categories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Platforms Table (For Platforms)
CREATE TABLE Platforms (
    platform_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Games Table (Now with developer & publisher as relationships)
CREATE TABLE Games (
    game_id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX),
    image_url NVARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    release_date DATE,
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Many-to-Many Relationship: Games & Developers
CREATE TABLE Game_Developers (
    game_id INT NOT NULL,
    developer_id INT NOT NULL,
    PRIMARY KEY (game_id, developer_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    FOREIGN KEY (developer_id) REFERENCES Developers(developer_id) ON DELETE CASCADE
);
GO

-- Many-to-Many Relationship: Games & Publishers
CREATE TABLE Game_Publishers (
    game_id INT NOT NULL,
    publisher_id INT NOT NULL,
    PRIMARY KEY (game_id, publisher_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES Publishers(publisher_id) ON DELETE CASCADE
);
GO

-- Many-to-Many Relationship: Games & Genres
CREATE TABLE Game_Genres (
    game_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES Genres(genre_id) ON DELETE CASCADE
);
GO

-- Many-to-Many Relationship: Games & Categories (Store Listing)
CREATE TABLE Game_Categories (
    game_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (game_id, category_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE CASCADE
);
GO

-- Many-to-Many Relationship: Games & Platforms
CREATE TABLE Game_Platforms (
    game_id INT NOT NULL,
    platform_id INT NOT NULL,
    PRIMARY KEY (game_id, platform_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id) ON DELETE CASCADE
);
GO

-- Create Coupons Table (Discount Codes, Expiry)
CREATE TABLE Coupons (
    coupon_id INT IDENTITY(1,1) PRIMARY KEY,
    code NVARCHAR(50) UNIQUE NOT NULL,
    discount_percentage INT CHECK (discount_percentage BETWEEN 1 AND 100),
    expiration_date DATE NOT NULL,
    usage_limit INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create Orders Table (Purchase History)
CREATE TABLE Orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    discount_code NVARCHAR(50) NULL,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (discount_code) REFERENCES Coupons(code) ON DELETE SET NULL
);
GO

-- Create Order Details Table (Track Purchased Games)
CREATE TABLE Order_Details (
    order_detail_id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    game_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id)
);
GO

-- Create Cart Table (Track User Carts)
CREATE TABLE Cart (
    cart_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    game_id INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id)
);
GO
