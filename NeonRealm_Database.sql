-- Create the Database
CREATE DATABASE NeonRealm;
GO

-- Use the Database
USE NeonRealm;
GO

-- Create Users Table (Login, Register, Google Auth)
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(255) UNIQUE NOT NULL,
    email NVARCHAR(255) UNIQUE NOT NULL,
    password_hash NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(255),
    phone NVARCHAR(20),
    google_auth_id NVARCHAR(255) NULL,  -- For Google login
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create Categories Table (Filter by Genre)
CREATE TABLE Categories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Platforms Table (Filter by Platform)
CREATE TABLE Platforms (
    platform_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) UNIQUE NOT NULL
);
GO

-- Create Games Table (Add, Edit, Delete, Display Games)
CREATE TABLE Games (
    game_id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX),
    image_url NVARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    release_date DATE,
    category_id INT NOT NULL,
    platform_id INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
);
GO

-- Create Orders Table (Purchase History, Checkout)
CREATE TABLE Orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    discount_code NVARCHAR(50) NULL,  -- This references Coupons table
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
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

-- Create Cart Table (Add/Remove Games in Cart)
CREATE TABLE Cart (
    cart_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    game_id INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (game_id) REFERENCES Games(game_id)
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

-- Fix: Connect Coupons to Orders
ALTER TABLE Orders
ADD CONSTRAINT FK_Orders_Coupons FOREIGN KEY (discount_code)
REFERENCES Coupons(code);
GO
