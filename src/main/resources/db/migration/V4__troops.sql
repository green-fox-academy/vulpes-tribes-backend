CREATE TABLE troops (ID INT AUTO_INCREMENT PRIMARY KEY,
kingdom_id INT,
level INT,
hp INT,
attack INT,
defence INT,
started_at BIGINT,
finished_at BIGINT,
FOREIGN KEY(kingdom_id)
REFERENCES kingdoms(id));
