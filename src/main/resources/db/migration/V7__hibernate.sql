ALTER TABLE kingdoms
ADD FOREIGN KEY (resources_id) REFERENCES resources(id);
CREATE TABLE hibernate_sequence (next_val INT);
insert into hibernate_sequence(next_val) values(0);