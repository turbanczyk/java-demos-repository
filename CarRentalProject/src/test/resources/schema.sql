/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  tomeku
 * Created: 12 lut 2022
 */

create table if not exists Car (
    id varchar(100) not null,
    model varchar(50) not null,
    brand varchar(50) not null,
    price_per_day real not null,
    number_of_seats integer not null,
    daily_kilometer_limit integer not null,
    gearbox_type varchar(50) not null,
    air_conditioning boolean not null,
    mileage integer not null,
    category varchar(50) not null,
    localization varchar(100) not null
);


create table if not exists User (
    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(100) not null,
    /*enabled boolean not null,*/
    first_name varchar(50) not null,
    surname varchar(50) not null,
    country varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    driving_license_number varchar(50) not null,
    email varchar(50) not null,
    telephone_number varchar(50) not null,
    primary key(id)
);

create table if not exists Order_Car (
    id bigint not null auto_increment,
    user_id long not null,
    car_id varchar(100) not null,
    start_date date not null,
    end_date date not null,
    placed_at date not null,
    total_price real not null
);

create table if not exists CreditCard (
    id varchar(100) not null,
    number integer not null,
    expiration date not null,
    cvv integer not null
);

/*
create table if not exists Car (
    id varchar(100) not null,
    model varchar(50) not null,
    brand varchar(50) not null,
    price_per_day real not null,
    number_of_seats integer not null,
    daily_kilometer_limit integer not null,
    gearbox_type varchar(50) not null,
    air_conditioning boolean not null,
    mileage integer not null,
    category varchar(50) not null,
    localization varchar(100) not null
);

create table if not exists User (
    id real not null,
    username varchar(100) not null,
    password varchar(100) not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    country varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    driving_license_number varchar(50) not null,
    email varchar(50) not null,
    telephone_number varchar(50) not null
);

create table if not exists Order_Car (
    id real not null,
    user_id long not null,
    car_id varchar(100) not null,
    start_date date not null,
    end_date date not null,
    placed_at date not null,
    total_price real not null
);

create table if not exists CreditCard (
    id varchar(100) not null,
    number integer not null,
    expiration date not null,
    cvv integer not null
);
*/
