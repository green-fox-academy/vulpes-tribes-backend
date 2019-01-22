ALTER TABLE kingdoms
ADD COLUMN tribeUser_id INT AFTER kingdomname;
ALTER TABLE users
ADD COLUMN kingdom_id INT AFTER password;