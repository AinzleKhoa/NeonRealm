-- Use the Database
USE NeonRealm;
GO

INSERT INTO Users (username, email, password_hash, full_name, phone, google_auth_id)
VALUES 
('user1', 'user1@example.com', 'hashed_pw1', 'John Doe', '123456789', NULL),
('user2', 'user2@example.com', 'hashed_pw2', 'Jane Smith', '987654321', 'google_123'),
('user3', 'user3@example.com', 'hashed_pw3', 'Alice Johnson', '555666777', NULL),
('user4', 'user4@example.com', 'hashed_pw4', 'Bob Williams', '111222333', 'google_456'),
('user5', 'user5@example.com', 'hashed_pw5', 'Charlie Brown', '999888777', NULL),
('user6', 'user6@example.com', 'hashed_pw6', 'David Clark', '444555666', 'google_789'),
('user7', 'user7@example.com', 'hashed_pw7', 'Emma Wilson', '666777888', NULL),
('user8', 'user8@example.com', 'hashed_pw8', 'Frank Thomas', '333222111', 'google_101'),
('user9', 'user9@example.com', 'hashed_pw9', 'Grace Hall', '888777666', NULL),
('user10', 'user10@example.com', 'hashed_pw10', 'Henry Adams', '111333555', 'google_202');

INSERT INTO Categories (name)
VALUES 
('Action'), ('Adventure'), ('RPG'), ('Strategy'), ('Simulation'),
('Sports'), ('Horror'), ('Shooter'), ('Puzzle'), ('Racing');

INSERT INTO Platforms (name)
VALUES 
('PC'), ('PlayStation 5'), ('Xbox Series X'), ('Nintendo Switch'), ('Mobile'),
('VR'), ('MacOS'), ('Linux'), ('Cloud Gaming'), ('Retro Console');

INSERT INTO Games (title, description, image_url, price, release_date, category_id, platform_id)
VALUES 
('Cyberpunk 2077', 'Futuristic open-world RPG', 'cyberpunk.jpg', 59.99, '2020-12-10', 3, 1),
('The Witcher 3', 'Fantasy RPG adventure', 'witcher3.jpg', 39.99, '2015-05-19', 3, 1),
('GTA V', 'Open-world crime game', 'gtav.jpg', 29.99, '2013-09-17', 1, 1),
('Elden Ring', 'Souls-like RPG with open-world', 'eldenring.jpg', 59.99, '2022-02-25', 3, 2),
('Halo Infinite', 'Sci-fi shooter', 'haloinfinite.jpg', 49.99, '2021-12-08', 8, 3),
('FIFA 23', 'Football simulation', 'fifa23.jpg', 59.99, '2022-09-30', 6, 2),
('Call of Duty MW3', 'Realistic military shooter', 'codmw3.jpg', 69.99, '2023-11-10', 8, 3),
('Minecraft', 'Sandbox survival game', 'minecraft.jpg', 19.99, '2011-11-18', 5, 4),
('Among Us', 'Multiplayer deception game', 'amongus.jpg', 4.99, '2018-06-15', 7, 5),
('Mario Kart 8', 'Racing arcade game', 'mariokart8.jpg', 49.99, '2014-05-29', 10, 4);

INSERT INTO Coupons (code, discount_percentage, expiration_date, usage_limit)
VALUES 
('DISCOUNT10', 10, '2025-12-31', 100),
('BLACKFRIDAY', 20, '2025-11-28', 50),
('SUMMER20', 20, '2025-06-30', 30),
('WELCOME5', 5, '2025-12-31', 200),
('GAMER50', 50, '2025-07-15', 10),
('FIFA23DEAL', 15, '2025-09-30', 25),
('CYBERMONDAY', 25, '2025-11-30', 40),
('HALO15', 15, '2025-10-10', 30),
('MARIO10', 10, '2025-05-20', 60),
('WITCHER20', 20, '2025-08-15', 35);

INSERT INTO Orders (user_id, total_price, discount_code)
VALUES 
(1, 59.99, 'DISCOUNT10'),
(2, 49.99, 'BLACKFRIDAY'),
(3, 39.99, 'SUMMER20'),
(4, 19.99, 'WELCOME5'),
(5, 69.99, 'GAMER50'),
(6, 29.99, NULL),
(7, 49.99, 'CYBERMONDAY'),
(8, 19.99, NULL),
(9, 59.99, 'HALO15'),
(10, 49.99, 'MARIO10');

INSERT INTO Order_Details (order_id, game_id, price)
VALUES 
(1, 1, 59.99),
(2, 2, 49.99),
(3, 3, 39.99),
(4, 8, 19.99),
(5, 6, 69.99),
(6, 4, 29.99),
(7, 5, 49.99),
(8, 9, 19.99),
(9, 7, 59.99),
(10, 10, 49.99);

INSERT INTO Cart (user_id, game_id)
VALUES 
(1, 2), (1, 3), 
(2, 5), (2, 6), 
(3, 1), (3, 7),
(4, 8), (4, 9),
(5, 10), (5, 4);
