ALTER TABLE resources
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdoms(id);


CREATE TABLE battles (id INT AUTO_INCREMENT PRIMARY KEY,
                    defender_id INT,
                    attacker_id INT,
                    FOREIGN KEY (defender_id) REFERENCES kingdoms(id),
                    FOREIGN KEY (attacker_id) REFERENCES kingdoms(id));

CREATE TABLE troops_battles(
                    troop_id INT,
                    battle_id INT,
                    FOREIGN KEY (troop_id) REFERENCES troops(id),
                    FOREIGN KEY (battle_id) REFERENCES battles(id));


