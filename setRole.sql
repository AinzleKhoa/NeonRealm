-- Use the Database
USE NeonRealm;
GO

SELECT *
FROM Users;

UPDATE Users
SET role = 'admin'
WHERE user_id = 1;