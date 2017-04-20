
/*
 * Author:  jayhixson
 * Created: April 14th, 2017
 */

DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Statuses;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Moderators;
DROP TABLE IF EXISTS Advertisements;

/* begin table creation */

CREATE TABLE IF NOT EXISTS Categories 
(Category_ID CHAR(3) NOT NULL,
CatName VARCHAR(20),
CONSTRAINT PK_CATEGORIES PRIMARY KEY(Category_ID)
);

CREATE TABLE IF NOT EXISTS Statuses
(Status_ID CHAR(2) NOT NULL,
Status_Name VARCHAR(20) NOT NULL,
CONSTRAINT PK_STATUSES PRIMARY KEY(Status_ID)
);

CREATE TABLE IF NOT EXISTS Users
(User_ID VARCHAR(10) NOT NULL,
Usr_FirstName VARCHAR(20) NOT NULL,
Usr_LastName VARCHAR(20) NOT NULL,
CONSTRAINT PK_USERS PRIMARY KEY(User_ID)
);

CREATE TABLE IF NOT EXISTS Moderators
(User_ID VARCHAR(10),
CONSTRAINT PK_MODERATORS PRIMARY KEY(User_ID),
CONSTRAINT FK_MODERATORS FOREIGN KEY(User_ID) REFERENCES Users(User_ID) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS Advertisements
(Advertisement_ID SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
AdvTitle VARCHAR(50) NOT NULL,
AdvDetails VARCHAR(50) NOT NULL,
AdvDateTime DATE NOT NULL,
Price DOUBLE(10,2) NOT NULL,
User_ID VARCHAR(10) NOT NULL,
Moderator_ID VARCHAR(10),
Category_ID CHAR(3) NOT NULL,
Status_ID CHAR(2) NOT NULL,
CONSTRAINT PK_ADVERTISEMENTS PRIMARY KEY(Advertisement_ID),
CONSTRAINT FK_ADVERTISEMENTS_USER_ID FOREIGN KEY(User_ID) REFERENCES Users(User_ID) ON DELETE CASCADE,
CONSTRAINT FK_ADVERTISEMENTS_MODERATOR_ID FOREIGN KEY(Moderator_ID) REFERENCES Moderators(User_ID) ON DELETE RESTRICT,
CONSTRAINT FK_ADVERTISEMENTS_CATEGORY_ID FOREIGN KEY(Category_ID) REFERENCES Categories(Category_ID) ON DELETE RESTRICT,
CONSTRAINT FK_ADVERTISEMENTS_STATUS_ID FOREIGN KEY(Status_ID) REFERENCES Statuses(Status_ID) ON DELETE RESTRICT
);

/* end table creation */

/* begin data population */
/* Categories */
INSERT INTO Categories(Category_ID,CatName)
VALUES('CAT','Cars and Trucks'),
('HOU','Housing'),
('ELC','Electronics'),
('CCA','Child Care');
/* Statuses */
INSERT INTO Statuses(Status_ID, Status_Name)
VALUES('PN','Pending'),
('AC','Active'),
('DI','Disapproved');
/* Users */
INSERT INTO Users(User_ID,Usr_FirstName,Usr_LastName)
VALUES('Jsmith','John','Smith'),
('ajackson','Ann','Jackson'),
('rkale','Rania','Kale'),
('Sali','Samir','Ali');
/* Moderators */
INSERT INTO Moderators(User_ID)
VALUES ('Jsmith'),('ajackson');
/* Advertisements */
INSERT INTO Advertisements(AdvTitle,AdvDetails,AdvDateTime,Price,Category_ID,User_ID,Moderator_ID,Status_ID)
VALUES('2010 Sedan Subaru','2010 sedan car in great shape for sale','2017-02-10','6000','CAT','rkale','Jsmith','AC'),
('Nice Office Desk','Nice office desk for sale','2017-02-15','50.25','HOU','rkale','Jsmith','AC'),
('Smart LG TV for $200 ONLY','Smart LG TV 52 inches! Really cheap!','2017-03-15','200','ELC','Sali', 'Jsmith','AC'),
('HD Tablet for $25 only','Amazon Fire Tablet HD','2017-03-20','25','ELC','rkale',NULL,'PN'),
('Laptop for $100','Amazing HP laptop for $100','2017-03-20','100','ELC','rkale',NULL,'PN');
/* end data population */

