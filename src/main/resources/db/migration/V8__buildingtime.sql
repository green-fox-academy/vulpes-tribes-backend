CREATE TABLE BUILDINGTIME (ID INT AUTO_INCREMENT PRIMARY KEY, type varchar (10), buildTimeInMin int);
INSERT INTO BUILDINGTIME (type, buildTimeInMin)
VALUES
  ('townhall', 3),
  ('mine', 1),
  ('farm', 1),
  ('barracks', 2);
