CREATE DATABASE book_shop;

USE book_shop;

CREATE TABLE book (
    bookId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    isTheBookInStore BOOLEAN NOT NULL,
    requestAmount INT NOT NULL,
    dateReceipted DATE NOT NULL,
    datePublished DATE NOT NULL,
    price INT
);
    
CREATE TABLE reader (
    readerId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);
    
CREATE TABLE request (
    requestId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bookId INT NOT NULL,
    readerId INT NOT NULL
);   
    
CREATE TABLE book_order (
    orderId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    readerId INT NOT NULL,
    dateExecuted DATE NOT NULL,
    price INT
);

CREATE TABLE order_history (
    orderId INT NOT NULL,
    bookId INT NOT NULL
);