CREATE DATABASE fridaydb;

USE fridaydb;

CREATE TABLE cookies (
  cookieID int(11) NOT NULL,
  LID int(11) DEFAULT NULL,
  Email varchar(45) DEFAULT NULL,
  Deadline bigint(45) NOT NULL,
  PRIMARY KEY (cookieID),
  KEY LID_idx (LID),
  KEY Email_idx (Email),
  CONSTRAINT Email FOREIGN KEY (Email) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT LID FOREIGN KEY (LID) REFERENCES lists (lid) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE list_categories (
  LCID int(11) NOT NULL,
  Name varchar(250) NOT NULL,
  Note text,
  Image varchar(250) DEFAULT NULL,
  Email varchar(250) DEFAULT NULL,
  PRIMARY KEY (LCID),
  KEY Email (Email),
  CONSTRAINT list_categories_ibfk_1 FOREIGN KEY (Email) REFERENCES users (email) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `lists` (
  LID int(11) NOT NULL,
  Name varchar(250) NOT NULL,
  Note text,
  Image varchar(250) DEFAULT NULL,
  LCID int(11) DEFAULT NULL,
  List_Owner varchar(250) DEFAULT NULL,
  CookieID int(11) DEFAULT NULL,
  PRIMARY KEY (LID),
  KEY lists_ibfk_1 (LCID),
  KEY lists_ibfk_2 (List_Owner),
  KEY CookieID_idx (CookieID),
  CONSTRAINT CookieID FOREIGN KEY (CookieID) REFERENCES cookies (cookieid) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT LCID FOREIGN KEY (LCID) REFERENCES list_categories (lcid) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT lists_ibfk_2 FOREIGN KEY (List_Owner) REFERENCES users (email) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE product_categories (
  PCID int(11) NOT NULL,
  Name varchar(250) NOT NULL,
  Note text,
  Logo varchar(250) DEFAULT NULL,
  Email varchar(250) DEFAULT NULL,
  PRIMARY KEY (PCID),
  KEY Email (Email),
  CONSTRAINT product_categories_ibfk_1 FOREIGN KEY (Email) REFERENCES users (email) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE product_lists (
  PID int(11) NOT NULL,
  LID int(11) NOT NULL,
  Quantity int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (PID, LID),
  KEY product_lists_LID_idx (LID),
  CONSTRAINT product_lists_LID FOREIGN KEY (LID) REFERENCES lists (lid) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT product_lists_PID FOREIGN KEY (PID) REFERENCES products (pid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE products (
  PID int(11) NOT NULL,
  Name varchar(250) NOT NULL,
  Note text,
  Logo varchar(250) DEFAULT NULL,
  Photo varchar(250) DEFAULT NULL,
  PCID int(11) DEFAULT NULL,
  Email varchar(250) DEFAULT NULL,
  PRIMARY KEY (PID),
  KEY products_ibfk_1 (PCID),
  KEY products_ibfk_2 (Email),
  CONSTRAINT products_ibfk_1 FOREIGN KEY (PCID) REFERENCES product_categories (pcid) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT products_ibfk_2 FOREIGN KEY (Email) REFERENCES users (email) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE sharing (
  Email varchar(250) NOT NULL,
  LID int(11) NOT NULL,
  Modify tinyint(1) DEFAULT '0',
  AddRemProd tinyint(1) DEFAULT '0',
  DeleteList tinyint(1) DEFAULT '0',
  PRIMARY KEY (Email, LID),
  KEY sharing_LID (LID),
  CONSTRAINT sharing_Email FOREIGN KEY (Email) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT sharing_LID FOREIGN KEY (LID) REFERENCES lists (lid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users (
  Email varchar(250) NOT NULL,
  Password varchar(250) NOT NULL,
  Name varchar(250) NOT NULL,
  Surname varchar(250) NOT NULL,
  Avatar varchar(250) DEFAULT NULL,
  Admin tinyint(1) DEFAULT '0',
  List_Owner tinyint(1) DEFAULT '0',
  Confirmed tinyint(1) DEFAULT NULL,
  PRIMARY KEY (Email)
);

