-- Use the Database
USE NeonRealm;
GO

BEGIN TRANSACTION;

DECLARE @order_id_user1 INT;

-- Insert Order for User (with SUMMER20 discount)
INSERT INTO Orders (user_id, total_price, discount_code)
VALUES (2, 95.98, 'SUMMER20');
SET @order_id_user1 = SCOPE_IDENTITY();

-- Insert Order Details for User
INSERT INTO Order_Details (order_id, game_id, price)
VALUES 
(@order_id_user1, 2, 69.99),  
(@order_id_user1, 4, 25.99),  
(@order_id_user1, 6, 29.99),   
(@order_id_user1, 7, 39.99),   
(@order_id_user1, 11, 19.99), 
(@order_id_user1, 13, 33.99),
(@order_id_user1, 14, 9.99),
(@order_id_user1, 19, 19.99),
(@order_id_user1, 21, 49.99), 
(@order_id_user1, 27, 59.99),
(@order_id_user1, 37, 9.99);

COMMIT TRANSACTION;

INSERT INTO Cart (user_id, game_id) VALUES
(2, 4),  -- User added GTA V to their cart
(2, 9);  -- User added DOOM Eternal to their cart
GO