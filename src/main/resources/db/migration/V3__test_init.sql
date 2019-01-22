ALTER TABLE kingdoms
ADD FOREIGN KEY (tribeUser_id) REFERENCES users(id);
ALTER TABLE users
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdoms(id);
