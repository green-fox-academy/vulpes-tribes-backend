CREATE TABLE RESOURCES (ID INT AUTO_INCREMENT PRIMARY KEY, AMOUNT INT, TYPE VARCHAR (45), UPDATED_AT INT, KINGDOM_ID INT, FOREIGN KEY(KINGDOM_ID) REFERENCES KINGDOMS(ID));