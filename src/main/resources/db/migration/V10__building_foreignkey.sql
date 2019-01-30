ALTER TABLE buildings
ADD COLUMN kingdom_id INT AFTER id;
ALTER TABLE buildings
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdoms(id);
