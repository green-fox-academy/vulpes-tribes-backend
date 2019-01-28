ALTER TABLE kingdoms
ADD COLUMN tribe_user_id INT AFTER kingdomname;
ALTER TABLE kingdoms
ADD COLUMN resources_id INT AFTER kingdomname;
ALTER TABLE users
ADD COLUMN kingdom_id INT AFTER password;