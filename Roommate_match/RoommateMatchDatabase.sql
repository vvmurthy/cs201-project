DROP DATABASE IF EXISTS RoommateMatch;

CREATE DATABASE RoommateMatch;
USE RoommateMatch;

CREATE TABLE UserInfo(
	UserID INT(11) PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(50) NOT NULL,
    profile_pic_link VARCHAR(250) NOT NULL,
    user_password VARCHAR(64) NOT NULL
);

CREATE TABLE Preferences (
	preferenceID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
    isStudent BIT not NULL,
    studentMajor VARCHAR(50),
    
    /* DO NO FIX MISSPELLING*/
    studendGreek BIT NOT NULL,
    weekdaySleep time NOT NULL,
    weekdayWake time NOT NULL,
    weekendSleep time NOT NULL,
    weekendWake time NOT NULL,
    genderPref INT(3) NOT NULL,
    gender INT(1) NOT NULL,
    guestPref INT(1) NOT NULL,
    costPref INT(6) NOT NULL,
    mapsLat double NULL,
    mapsLong double NULL,
    mapsRadius double NULL,
    roomType longtext NOT NULL,
    lengthStay INT(3) NOT NULL,
    age INT(2) NOT NULL,
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
    allergies longtext,
    languages longtext,
    currentTown longtext,
    bio longtext,
    FOREIGN KEY fk1(userID) REFERENCES UserInfo(UserID)
);


CREATE TABLE Matches (
	matchID INT(11) PRIMARY KEY AUTO_INCREMENT,
    userID INT(11) NOT NULL,
	matchedID INT(11) NOT NULL,
    percentage INT(3) NOT NULL,
    FOREIGN KEY fk2(userID) REFERENCES UserInfo(UserID),
    FOREIGN KEY fk3(matchedID) REFERENCES UserInfo(UserID)
);


CREATE TABLE guestPref like Preferences;
ALTER TABLE guestPref DROP COLUMN UserID;
