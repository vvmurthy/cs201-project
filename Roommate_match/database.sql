DROP database if exists UserInfo;
CREATE database UserInfo;
USE UserInfo;

CREATE table users(
	UserID int(11) primary key not null auto_increment, 
    fullname varchar(100) not null,
    email varchar(100) not null,
    profile_pic_link varchar(300) not null,
    user_password varchar(64) not null,
    hometown varchar(1000),
    current_town varchar(1000),
    bio longtext
);