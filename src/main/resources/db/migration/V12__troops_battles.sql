ALTER TABLE resources
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdoms(id);


CREATE TABLE battles (id INT AUTO_INCREMENT PRIMARY KEY,
                    defender_id INT,
                    attacker_id INT);

ALTER TABLE battles
ADD FOREIGN KEY (defender_id) REFERENCES kingdoms(id);

ALTER TABLE battles
ADD FOREIGN KEY (attacker_id) REFERENCES kingdoms(id);

CREATE TABLE troops_battles(
                    troop_id INT,
                    battle_id INT);

ALTER TABLE troops_battles
ADD FOREIGN KEY (troop_id) REFERENCES troops(id);

ALTER TABLE troops_battles
ADD FOREIGN KEY (battle_id) REFERENCES battles(id);


