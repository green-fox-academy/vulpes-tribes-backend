CREATE TABLE buildings (id INT AUTO_INCREMENT PRIMARY KEY,
                        kingdom_id INT,
                        type VARCHAR(45),
                        level SMALLINT (4),
                        hp SMALLINT (4),
                        stardet_at SMALLINT (10),
                        finished_at SMALLINT (10),
                        FOREIGN KEY(kingdom_id) REFERENCES kingdom(id));
