DROP DATABASE IF EXISTS RoommateMatch;

CREATE DATABASE RoommateMatch;
USE RoommateMatch;

CREATE TABLE UserInfo(
	UserID INT(11) PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(50) NOT NULL,
    profile_pic_link VARCHAR(250) NOT NULL,
    user_password BINARY(64) NOT NULL,
    hometown VARCHAR(50),
    currentTown VARCHAR(50),
    bio VARCHAR(500)
);

CREATE TABLE Preferences (
	preferenceID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
    studentMajor VARCHAR(50),
    studendGreek BIT(1) NOT NULL,
    weekdaySleep time NOT NULL,
    weekdayWake time NOT NULL,
    weekendSleep time NOT NULL,
    weekendWake time NOT NULL,
    genderPref INT(3) NOT NULL,
    gender INT(1) NOT NULL,
    costPref INT(6) NOT NULL,
    mapsLat double NOT NULL,
    mapsLong double NOT NULL,
    mapsRadius INT(11) NOT NULL,
    roomType VARCHAR(10) NOT NULL,
    lengthStay INT(3) NOT NULL,
    ageGroup INT(2) NOT NULL,
    pets INT(1) NOT NULL,
    petsPref INT(1) NOT NULL,
    smoking INT(1) NOT NULL,
    smokingPref INT(1) NOT NULL,
    drinking INT(1) NOT NULL,
    drinkingPref INT(1) NOT NULL,
    dishes INT(1) NOT NULL,
    dishesPref INT(1) NOT NULL,
    cleanliness INT(1) NOT NULL,
    cleanlinessPref INT(1) NOT NULL,
    sharingFood INT(1) NOT NULL,
    borrowing INT(1) NOT NULL,
    FOREIGN KEY fk1(userID) REFERENCES UserInfo(userID)
);


CREATE TABLE Matches (
	matchID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
	matchedID INT(11) NOT NULL,
    percentage INT(3) NOT NULL,
    FOREIGN KEY fk2(userID) REFERENCES UserInfo(userID),
    FOREIGN KEY fk3(matchedID) REFERENCES UserInfo(userID)
);