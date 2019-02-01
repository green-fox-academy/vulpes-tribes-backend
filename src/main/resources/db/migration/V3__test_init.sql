ALTER TABLE kingdoms
ADD FOREIGN KEY (tribe_user_id) REFERENCES users(id);
