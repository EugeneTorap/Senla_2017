CREATE DATABASE creation;

USE creation;

CREATE TABLE product (
    maker VARCHAR(50) NOT NULL,
    model VARCHAR(50) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL
);
    
CREATE TABLE pc (
    code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    speed SMALLINT NOT NULL,
    ram SMALLINT NOT NULL,
    hd REAL NOT NULL,
    cd VARCHAR(10) NOT NULL,
    price INT
);
    
CREATE TABLE laptop (
    code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    speed SMALLINT NOT NULL,
    ram SMALLINT NOT NULL,
    hd REAL NOT NULL,
    price INT,
    screen TINYINT NOT NULL
);   
    
CREATE TABLE printer (
    code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    color CHAR(1) NOT NULL,
    type VARCHAR(10) NOT NULL,
    price INT
);
