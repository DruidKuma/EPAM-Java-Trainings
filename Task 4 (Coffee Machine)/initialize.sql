/*
Configuration for easy launching mysql prompt from the terminal:

export MYSQL_HOME=/usr/local/mysql
alias mysql='sudo $MYSQL_HOME/bin/mysql'
*/

drop database if exists coffeemachine;
create database coffeemachine;
ALTER DATABASE coffeemachine DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use coffeemachine;

create table users (id int not null auto_increment, login text, name text, surname text, email text, account decimal(6,2), role int, primary key (id));
insert into users (login, name, surname, email, account, role) values
('Linux', 'Linus', 'Torwalds', 'linuxtor@gmail.com', 1000, 2),
('Apple', 'Steve', 'Jobs', 'steevy@gmail.com', 1000, 1),
('Microsoft', 'Bill', 'Gates', 'gatetoheaven@gmail.com', 1000, 1),
('DruidKuma', 'Yuriy', 'Miedviediev', 'druidkuma@gmail.com', 1000, 1),
('Mikulash', 'Nikolay', 'Alyoshyn', 'alyoshyn.np@gmail.com', 1000, 1),
('Svetik', 'Svetlana', 'Lobanova', 'lobanovass@gmail.com', 1000, 1),
('Freddy', 'Fred', 'Flinstone', 'flinstone@gmail.com', 1000, 1),
('Vampire', 'Damon', 'Salvatore', 'vampire@gmail.com', 1000, 1),
('Sorcerer', 'Gendalf', 'White', 'magician@gmail.com', 1000, 1),
('Медвед', 'Юрий', 'Медведев', 'hacker@gmail.com', 1000, 2);

create table passwords(userID int unique, password text);
insert into passwords values
(1, 'qwerty'),
(2, '12345'),
(3, 'q1w2e3'),
(4, '798397'),
(5, '54321'),
(6, 'ytrewq'),
(7, '1111'),
(8, '1q2w3e'),
(9, 'magic'),
(10, 'super');

create table drinks (id INT NOT NULL AUTO_INCREMENT, title text, primary key(id));
insert into drinks (title) values
('Ristretto'),
('Espresso'),
('Americano'),
('Cappuccino'),
('Latte');

create table ingredients (id INT NOT NULL AUTO_INCREMENT, title text, amount int, primary key (id));
insert into ingredients (title, amount) values
('Coffee', 1000),
('Water', 10000),
('Milk', 5000),
('Sugar', 1000),
('Syrop', 1000),
('Cream', 1000),
('Chocolate', 1000),
('Cinnamon', 200);

create table recipe (drinkID int, ingrID int, amount int, primary key (drinkID, ingrID));
insert into recipe values
(1, 1, 8),
(1, 2, 15),
(2, 1, 8),
(2, 2, 30),
(3, 1, 8),
(3, 2, 60),
(4, 1, 8),
(4, 2, 30),
(4, 3, 150),
(5, 1, 8),
(5, 2, 30), 
(5, 3, 200);

create table drinkPrices (drinkID int, price decimal(4,2));
insert into drinkPrices values
(1, 15),
(2, 20), 
(3, 25),
(4, 30),
(5, 35);

create table ingrPrices (ingrID int, price decimal(4,2));
insert into ingrPrices values
(1, 1.5),
(2, 0.02),
(3, 0.05),
(4, 0.5),
(5, 1),
(6, 1),
(7, 1),
(8, 1); 
