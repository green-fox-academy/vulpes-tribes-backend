CREATE TABLE battles(id AUTO_INCREMENT PRIMARY KEY,
                    defender_id VARCHAR (45),
                    attacker_id VARCHAR (45));

ALTER TABLE battles
ADD FOREIGN KEY (defender_id) REFERENCES kingdoms(id);

ALTER TABLE battles
ADD FOREIGN KEY (atacker_id) REFERENCES kingdoms(id);

CREATE TABLE troops_battles(
                    troops_id INT,
                    battles_id INT)

