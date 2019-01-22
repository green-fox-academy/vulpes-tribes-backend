CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(45), password VARCHAR(90), resources_id INT, logged_in BIT );
CREATE TABLE kingdoms (id INT AUTO_INCREMENT PRIMARY KEY, kingdomname VARCHAR(45),tribe_User_id INT);
