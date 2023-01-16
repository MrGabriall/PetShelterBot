-- PETS
insert into pet(name, date_of_adoption)
values ('Fly', '2023-01-08 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Good boy', '2023-01-03 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Nike', '2023-01-05 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Gray', '2022-12-08 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Fil', '2020-01-04 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Crazy', '2023-01-11 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Boom', '2023-01-10 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Pit', '2023-01-11 21:30:00.000000');

insert into pet(name, date_of_adoption)
values ('Claus', '2023-01-03 21:30:00.000000');

-- VOLUNTEERS
insert into volunteer(chat_id, last_name, first_name)
values (185364843, 'Safronov', 'Nikolay');

insert into volunteer(chat_id, last_name, first_name)
values (185364845, 'Petrov', 'Ivan');

insert into volunteer(chat_id, last_name, first_name)
values (185364823, 'Dianova', 'Lina');



-- OWNERS
insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364843, 'Popova', 'Nadia', +7123456-78-90, 30, 1, 1);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364856, 'Savenkova', 'Anna', +7123456-78-43, 30, 2, 2);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364898, 'Ushakova', 'Olga', +7123456-78-65, 30, 3, 3);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364824, 'Sazonova', 'Inna', +7123456-78-23, 30, 1, 4);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364835, 'Voinov', 'Nikita', +7123456-78-87, 30, 2, 5);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364824, 'Platonov', 'Eugene', +7123456-78-45, 30, 3, 6);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364898, 'Samorai', 'Valeriy', +7123456-78-98, 30, 1, 7);

insert into owner(chat_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364834, 'Filatov', 'Michael', +7123456-78-23, 30, 2, 8);