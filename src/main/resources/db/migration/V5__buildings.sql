CREATE TABLE BUILDINGS (ID INT AUTO_INCREMENT PRIMARY KEY,
                        KINGDOM_ID INT,
                        TYPE VARCHAR(45),
                        LEVEL SMALLINT (4),
                        HP SMALLINT (4),
                        STARTED_AT SMALLINT (10),
                        FINISHED_AT SMALLINT (10),
                        FOREIGN KEY(KINGDOM_ID) REFERENCES KINGDOMS(ID));

