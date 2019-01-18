ALTER TABLE kingdom
ADD FOREIGN KEY (tribe_User_id) REFERENCES users(id);
ALTER TABLE users
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdom(id);
