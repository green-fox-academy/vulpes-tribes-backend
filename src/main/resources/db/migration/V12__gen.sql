--ALTER TABLE resources
--ADD COLUMN generated_amount BIGINT(10) AFTER updated_at;
CREATE TABLE battles(id AUTO_INCREMENT PRIMARY KEY,
                    defender VARCHAR (45),
                    attacker VARCHAR (45)