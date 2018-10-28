DROP DATABASE IF EXISTS RoommateMatch;

CREATE DATABASE RoommateMatch;
USE RoommateMatch;

CREATE TABLE UserInfo(
	userID INT(11) PRIMARY KET AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    pic VARCHAR(250) NOT NULL,
    password VARCHAR(25) NOT NULL,
    hometown VARCHAR(50) NOT NULL,
    currentTown VARCHAR(50) NOT NULL,
    bio VARCHAR(500) NOT NULL
);

CREATE TABLE Preferences (
	perferenceID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
    studentMajor VARCHAR(50) NULL,
    studendGreek BIT(1) NULL,
    weekdaySleep VARCHAR(10) NOT NULL,
    weekdayWake VARCHAR(10) NOT NULL,
    weekendSleep VARCHAR(50) NOT NULL,
    weekendWake VARCHAR(50) NO NULL,
    genderPref INT(3) NOT NULL,
    gender INT(1) NOT NULL,
    costPref INT(6) NOT NULL,
    mapsLat INT(11) NOT NULL,
    mapsLong INT(11) NOT NULL,
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