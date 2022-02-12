/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  tomeku
 * Created: 12 lut 2022
 */
/*
create table if not exists Cars (
    id varchar(100) not null
);
*/


create table if not exists Cars (
    id varchar(100) not null,
    model varchar(50) not null,
    brand varchar(50) not null,
    pricePerDay real not null,
    numberOfSeats integer not null,
    dailyKilometerLimit integer not null,
    gearboxType varchar(50) not null,
    airConditioning boolean not null,
    mileage integer not null,
    category varchar(50) not null,
    localization varchar(100) not null
);
