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



delete from Order_Car;
insert into Order_Car values ('zamowienie-1-id', 'user-1-id', 
    'samochod-1-id', '2022-02-15', '2022-02-18', '2022-01-15');
insert into Order_Car values ('zamowienie-2-id', 'user-1-id', 
    'samochod-1-id', '2022-01-15', '2022-02-18', '2022-01-15');
insert into Order_Car values ('zamowienie-3-id', 'user-2-id', 
    'samochod-2-id', '2022-01-23', '2022-02-18', '2022-01-15');