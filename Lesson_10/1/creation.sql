create database creation;

use creation;

create table product(
	maker varchar(50) not null,
    model varchar(50) primary key not null,
    type varchar(50) not null);
    
create table pc(
	code int primary key not null,
    model varchar(50) not null,
    speed smallint not null,
    ram smallint not null,
    hd real not null,
    cd varchar(10) not null,
    price int);
    
create table laptop(
	code int primary key not null,
    model varchar(50) not null,
    speed smallint not null,
    ram smallint not null,
    hd real not null,
    price int,
    screen tinyint not null);   
    
create table printer(
	code int primary key not null,
    model varchar(50) not null,
    color char(1) not null,
    type varchar(10) not null,
    price int);
