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
insert into Order_Car values ('zamowienie-1-id', 'user-1-id', 
    'samochod-2-id', '2022-01-19', '2022-01-21', '2022-01-15');
insert into Order_Car values ('zamowienie-2-id', 'user-1-id', 
    'samochod-5-id', '2022-01-19', '2022-01-22', '2022-01-15');
insert into Order_Car values ('zamowienie-3-id', 'user-2-id', 
    'samochod-6-id', '2022-01-19', '2022-01-23', '2022-01-15');
insert into Order_Car values ('zamowienie-4-id', 'user-2-id', 
    'samochod-2-id', '2022-01-22', '2022-01-24', '2022-01-15');
insert into Order_Car values ('zamowienie-5-id', 'user-2-id', 
    'samochod-7-id', '2022-01-22', '2022-01-25', '2022-01-15');
insert into Order_Car values ('zamowienie-6-id', 'user-2-id', 
    'samochod-1-id', '2022-01-23', '2022-01-24', '2022-01-15');
insert into Order_Car values ('zamowienie-7-id', 'user-2-id', 
    'samochod-8-id', '2022-01-23', '2022-01-27', '2022-01-15');
insert into Order_Car values ('zamowienie-8-id', 'user-2-id', 
    'samochod-1-id', '2022-01-25', '2022-01-27', '2022-01-15');
insert into Order_Car values ('zamowienie-9-id', 'user-2-id', 
    'samochod-3-id', '2022-01-26', '2022-01-27', '2022-01-15');
insert into Order_Car values ('zamowienie-10-id', 'user-2-id', 
    'samochod-4-id', '2022-01-29', '2022-01-30', '2022-01-15');
insert into Order_Car values ('zamowienie-11-id', 'user-2-id', 
    'samochod-4-id', '2022-01-23', '2022-01-24', '2022-01-15');