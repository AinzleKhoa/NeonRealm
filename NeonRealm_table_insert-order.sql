-- Use the Database
USE NeonRealm;
GO

BEGIN TRANSACTION;

DECLARE @order_id_user1 INT;

-- Insert Order for User (with SUMMER20 discount)
INSERT INTO Orders (user_id, total_price, discount_code)
VALUES (5, 95.98, 'SUMMER20');
SET @order_id_user1 = SCOPE_IDENTITY();

-- Insert Order Details for User
INSERT INTO Order_Details (order_id, game_id, price)
VALUES 
(@order_id_user1, 13, 69.99),  
(@order_id_user1, 12, 25.99),  
(@order_id_user1, 7, 29.99),   
(@order_id_user1, 9, 39.99),   
(@order_id_user1, 15, 19.99), 
(@order_id_user1, 18, 33.99),
(@order_id_user1, 25, 9.99),
(@order_id_user1, 30, 19.99),
(@order_id_user1, 35, 49.99), 
(@order_id_user1, 40, 59.99),
(@order_id_user1, 38, 9.99);

COMMIT TRANSACTION;

INSERT INTO Cart (user_id, game_id) VALUES
(1, 3),  -- User added GTA V to their cart
(1, 8);  -- User added DOOM Eternal to their cart
GO