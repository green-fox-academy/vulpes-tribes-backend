CREATE TABLE locations (id INT AUTO_INCREMENT PRIMARY KEY ,
kingdom_id INT,
x INT,
y INT,
FOREIGN KEY(kingdom_id)
REFERENCES kingdoms(id));
