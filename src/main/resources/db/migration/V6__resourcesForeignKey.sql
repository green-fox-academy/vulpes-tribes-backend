ALTER TABLE resources
ADD FOREIGN KEY (kingdom_id) REFERENCES kingdom(id);