-- DROP TABLE BOOK;

CREATE TABLE BOOK 
(
	bid VARCHAR(20) NOT NULL PRIMARY KEY,
	title VARCHAR (60) NOT NULL,
	price DOUBLE NOT NULL,
	category VARCHAR(20) NOT NULL
);

INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002', 'Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003', 'Mechanics', 100, 'Engineering');
INSERT INTO Book (bid, title, price, category) VALUES ('b004', 'Parasite Eve', 24.99, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b005', 'Romance of the Three Kingdoms vol. 1', 50.49, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b006', 'Ringu', 17.95, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b007', 'On the Origin of Species', 8.42, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b008', 'Theory of Machines', 47.59, 'Engineering');
INSERT INTO Book (bid, title, price, category) VALUES ('b009', 'Spiral', 16.78, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b010', 'Birthday', 15.19, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b011', 'Dracula', 11.64, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b012', 'Cosmos', 108.20, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b013', 'Engineering for Dummies', 19.31, 'Engineering');
INSERT INTO Book (bid, title, price, category) VALUES ('b014', 'The Lord of the Rings', 170, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b015', 'Nineteen Eighty-Four', 19.84, 'Fiction');

-- DROP TABLE Accounts;

CREATE TABLE Accounts
(
	uid INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	uname VARCHAR(20) NOT NULL UNIQUE,
	salt INT NOT NULL,
	pass CHAR(128) NOT NULL,
	utype SMALLINT NOT NULL,
	defaultAddress INT NOT NULL DEFAULT 0
);

INSERT INTO Accounts (uname,salt,pass,utype,defaultAddress) VALUES ('giomun', -1085813333, '7bbbcf57e10403009b01ff85ce075f7f5a8802daa459a9d17e551e14d1f0ee4170b19179299146f46b0918202c232061fbc563bc7e3a96fecd8ec692f5aeec65', 1, 4);
INSERT INTO Accounts (uname,salt,pass,utype,defaultAddress) VALUES ('admin', 853665647, '9a8a0f3878827512bd87d6d9e1ee722f994e6356781150c1fee1898980042541dedc991c78cf1d9df6ea34f90d7573495a7e5f658d50164d791a93570f500fab', 1, 5);
INSERT INTO Accounts (uname,salt,pass,utype,defaultAddress) VALUES ('testuser', 	1816326156, 'bc5e2b49e9cabd8425ce3d5b40f1ed0e49197bc32ff36203b2508515301e0a335f5911bcbd98553b40ac98802100f8eae66320662abfabfb4027a0a823e2d96e', 3, 0);
INSERT INTO Accounts (uname,salt,pass,utype,defaultAddress) VALUES ('Nicholas', 	-832877658, '0a8904b2bd6fd3d8003b88d6af192c0687e0ec0996c20be183da1b85c231b53fb231f46defa301157c8091f718f72932425c51ba8e1b1f28271d1ea4dfdf9cab', 1, 3);
INSERT INTO Accounts (uname,salt,pass,utype,defaultAddress) VALUES ('Diba', 	-1245449202, 'c14068b94e34d7eb91b77637975d9605d54719440cc50b6a89d179d7768bbc75e7f5e8a708bb89e3b7d285ea78aba6fe3d1fd615134a41a4023be223a7ddb189', 1, 2);

-- DROP TABLE Address;

CREATE TABLE Address (
	id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	street VARCHAR(100) NOT NULL,
	province VARCHAR (20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	zip VARCHAR(20) NOT NULL,
	phone VARCHAR(20),
	uid VARCHAR(20) NOT NULL,
	lname VARCHAR(20) NOT NULL,
	fname VARCHAR(20) NOT NULL
);

INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('123 Yonge St', 'ON', 'Canada', 'K1E 6T5', '647-123-4567', 1, 'Giovannini', 'Dylan');
INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('445 Avenue Rd', 'ON', 'Canada', 'M1C 6K5', '416-123-8569', 5, 'Jafarnejad', 'Diba');
INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('789 Keele St', 'ON', 'Canada', 'K3C 9T5', '416-123-9568', 4, 'Venditti', 'Nicholas');
INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('939 Kingsway Dr', 'ON', 'Canada', 'L7L 1C5', '905-638-9521', 1, 'Gio', 'Dyl');
INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('123 Fake St', 'ON', 'Canada', 'H0H 0H0', '123-456-7890', 2, 'Last', 'First');
INSERT INTO Address (street, province, country, zip, phone, uid, lname, fname) VALUES ('321 Ekaf St', 'ON', 'Canada', 'L0L 0L0', '098-765-4321', 2, 'Tsal', 'Tsrif');

-- DROP TABLE Reviews;

CREATE TABLE Reviews
(
	rid INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	bid VARCHAR(20) NOT NULL REFERENCES Book (bid),
	uname VARCHAR(20) NOT NULL REFERENCES Accounts (uname),
	rating INT NOT NULL,
	review VARCHAR(480) NOT NULL
);

INSERT INTO Reviews (bid, uname, rating, review) VALUES ('b005', 'giomun', 5, 'Among men Lu Bu. Among horses Red Hare.');
INSERT INTO Reviews (bid, uname, rating, review) VALUES ('b004', 'giomun', 4, 'Creepy... but interesting.');
INSERT INTO Reviews (bid, uname, rating, review) VALUES ('b001', 'giomun', 2, 'I have not read this book.');

-- DROP TABLE PO;

CREATE TABLE PO (
	id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	lname VARCHAR(20) NOT NULL,
	fname VARCHAR(20) NOT NULL,
	status SMALLINT NOT NULL,
	address INT NOT NULL
);

CREATE INDEX address_index ON PO (address);

ALTER TABLE PO FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE;

INSERT INTO PO (lname, fname, status, address) VALUES ('Giovannini', 'Dylan', 1, '1');
INSERT INTO PO (lname, fname, status, address) VALUES ('Jafarnejad', 'Diba', 2, '2');
INSERT INTO PO (lname, fname, status, address) VALUES ('Venditti', 'Nicholas', 3, '3');
INSERT INTO PO (lname, fname, status, address) VALUES ('Giovannini', 'Dylan', 1, '1');
INSERT INTO PO (lname, fname, status, address) VALUES ('Jafarnejad', 'Diba', 2, '2');
INSERT INTO PO (lname, fname, status, address) VALUES ('Venditti', 'Nicholas', 3, '3');

-- DROP TABLE POItem;

CREATE TABLE POItem (
	id INT NOT NULL,
	bid VARCHAR(20) NOT NULL,
	price DOUBLE NOT NULL,
	PRIMARY KEY(id, bid)
);

CREATE INDEX id_index ON POItem (id);
CREATE INDEX bid_index ON POItem (bid);

ALTER TABLE POItem FOREIGN KEY (id) REFERENCES PO (id) ON DELETE CASCADE;
ALTER TABLE POItem FOREIGN KEY (bid) REFERENCES Book (bid) ON DELETE CASCADE;

INSERT INTO POItem (id, bid, price) VALUES (1, 'b001', '20');
INSERT INTO POItem (id, bid, price) VALUES (2, 'b002', '201');
INSERT INTO POItem (id, bid, price) VALUES (3, 'b003', '100');
INSERT INTO POItem (id, bid, price) VALUES (4, 'b015', '19.84');
INSERT INTO POItem (id, bid, price) VALUES (5, 'b015', '19.84');
INSERT INTO POItem (id, bid, price) VALUES (6, 'b015', '19.84');

-- DROP TABLE VisitEvent;

CREATE TABLE VisitEvent (
	eid INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	day VARCHAR(8) NOT NULL,
	bid VARCHAR(20) NOT NULL REFERENCES Book (bid),
	eventtype SMALLINT NOT NULL
);

INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b001', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b002', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b002', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b002', 3);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b001', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b003', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b003', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b003', 3);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', 'b001', 3);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12122015', 'b015', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12122015', 'b015', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12122015', 'b015', 3);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12152015', 'b015', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12152015', 'b015', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12152015', 'b015', 3);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12182015', 'b015', 1);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12182015', 'b015', 2);
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12182015', 'b015', 3);