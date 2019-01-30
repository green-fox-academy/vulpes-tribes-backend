CREATE TABLE buildingtime (id INT AUTO_INCREMENT PRIMARY KEY, type varchar (10), build_time_in_min int);
INSERT INTO buildingtime (type, build_time_in_min)
VALUES
  ('townhall', 3),
  ('mine', 1),
  ('farm', 1),
  ('barracks', 2);
