ALTER TABLE kingdoms
ADD FOREIGN KEY (tribe_user_id) REFERENCES users(id);
ALTER TABLE users
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdoms(id);
