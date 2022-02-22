/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  tomeku
 * Created: 12 lut 2022
 */


delete from Car;
insert into Car values ('samochod-1-id', 'Mondeo', 'Ford',
    '130', '5', '400', 'manualna', 'true', '12345', 'małe', 'Częstochowa');
insert into Car values ('samochod-2-id', 'Mondeo', 'Ford',
    '142', '4', '500', 'automatyczna', 'false', '20341', 'średnie', 'Rybnik');
insert into Car values ('samochod-3-id', 'Focus', 'Ford',
    '114', '5', '300', 'manualna', 'true', '15341', 'kombi', 'Gliwice');
insert into Car values ('samochod-4-id', 'BMW', 'Seria 3',
    '214', '5', '300', 'manualna', 'true', '15341', 'kombi', 'Gliwice');
insert into Car values ('samochod-5-id', 'BMW', 'x5',
    '340', '5', '300', 'manualna', 'true', '15341', 'SUV', 'Rybnik');
insert into Car values ('samochod-6-id', 'BMW', 'x5',
    '340', '5', '300', 'manualna', 'true', '15341', 'SUV', 'Gliwice');
insert into Car values ('samochod-7-id', 'Opel', 'Astra',
    '140', '5', '300', 'manualna', 'true', '15341', 'średnie', 'Rybnik');
insert into Car values ('samochod-8-id', 'Opel', 'Astra',
    '140', '5', '300', 'manualna', 'true', '22341', 'średnie', 'Katowice');


delete from Order_Car;
insert into Order_Car values ('1', '1', 
    'samochod-2-id', '2022-01-19', '2022-01-21', '2022-01-15', '432');
insert into Order_Car values ('2', '1', 
    'samochod-5-id', '2022-01-19', '2022-01-22', '2022-01-15', '432');
insert into Order_Car values ('3', '2', 
    'samochod-6-id', '2022-01-19', '2022-01-23', '2022-01-15', '432');
insert into Order_Car values ('4', '2', 
    'samochod-2-id', '2022-01-22', '2022-01-24', '2022-01-15', '432');
insert into Order_Car values ('5', '2', 
    'samochod-7-id', '2022-01-22', '2022-01-25', '2022-01-15', '432');
insert into Order_Car values ('6', '2', 
    'samochod-1-id', '2022-01-23', '2022-01-24', '2022-01-15', '432');
insert into Order_Car values ('7', '2', 
    'samochod-8-id', '2022-01-23', '2022-01-27', '2022-01-15', '432');
insert into Order_Car values ('8', '2', 
    'samochod-1-id', '2022-01-25', '2022-01-27', '2022-01-15', '432');
insert into Order_Car values ('9', '2', 
    'samochod-3-id', '2022-01-26', '2022-01-27', '2022-01-15', '432');
insert into Order_Car values ('10', '2', 
    'samochod-4-id', '2022-01-29', '2022-01-30', '2022-01-15', '432');
insert into Order_Car values ('11', '2', 
    'samochod-4-id', '2022-01-23', '2022-01-24', '2022-01-15', '432');

delete from User;
insert into User values('1', 'user1', 'password1', 
    'Jan', 'Kowalski', 'Poland', 'Katowice', 'Orzeszkowa', 'DHEKD-3432', 
    'jan@kowalski.com', '123-456-789');
insert into User values('2', 'user2', 'password2', 
    'Jerzy', 'Nowak', 'Poland', 'Katowice', 'Poziomkowa', 'DHEKD-8632', 
    'jerzy@nowak.com', '223-456-789');