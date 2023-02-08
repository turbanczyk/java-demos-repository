CREATE DATABASE `credentials`;

USE `credentials`;

CREATE TABLE users
(
    email     VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NULL,
    lastname  VARCHAR(255) NULL,
    password  VARCHAR(255) NULL,
    `role`    VARCHAR(255) NULL,
    CONSTRAINT pk__user PRIMARY KEY (email)
);

insert  into `users`(`email`,`firstname`,`lastname`,`password`,`role`) values 
('john@king.com','John','King','mypassword','USER'),
('ted@polansky.com','Ted','Polansky','mypassword2','USER');