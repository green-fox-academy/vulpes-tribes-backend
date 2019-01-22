
CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(45), password VARCHAR(90),kingdom_id INT,resources_id INT, loggedIn BIT );
CREATE TABLE kingdom (id INT AUTO_INCREMENT PRIMARY KEY, kingdomname VARCHAR(45),tribe_User_id INT);

