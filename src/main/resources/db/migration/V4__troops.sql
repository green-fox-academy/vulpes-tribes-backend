CREATE TABLE troops (ID INT AUTO_INCREMENT PRIMARY KEY, KINGDOM_ID INT, LEVEL INT, HP INT, ATTACK INT, DEFENCE INT, STARTED_AT INT, FINISHED_AT INT,  FOREIGN KEY(KINGDOM_ID) REFERENCES KINGDOMS(ID));
