ALTER TABLE KINGDOMS
ADD FOREIGN KEY (tribeUser_id) REFERENCES USERS(id);
ALTER TABLE USERS
ADD FOREIGN KEY (kingdom_id) REFERENCES KINGDOMS(id);
