-- liquibase formatted sql

-- changeSet nadillustrator:1
drop table if exists pet cascade;
create table pet
(
    id               BIGSERIAL NOT NULL PRIMARY KEY,
    name             TEXT      not null,
    date_of_adoption DATE      not null
);

-- changeSet nadillustrator:2
drop table if exists photo cascade;
create table photo
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    file_id TEXT      not null
);

-- changeSet nadillustrator:3
drop table if exists volunteer cascade;
create table volunteer
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    chat_id    INT8,
    last_name  TEXT,
    first_name TEXT
);

-- changeSet nadillustrator:4
drop table if exists user_state cascade;
create table user_state
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    chat_id INT8,
    state   TEXT
);

-- changeSet nadillustrator:5
drop table if exists owner cascade;
create table owner
(
    id                    BIGSERIAL NOT NULL PRIMARY KEY,
    chat_id               INT8,
    last_name             TEXT,
    first_name            TEXT,
    phone_number          TEXT,
    number_of_report_days INT4,
    volunteer_id          INT8 REFERENCES volunteer (id),
    pet_id                INT8 REFERENCES pet (id)
);

-- changeSet nadillustrator:6
drop table if exists report cascade;
create table report
(
    id                   BIGSERIAL NOT NULL PRIMARY KEY,
    incoming_report_date DATE not null,
    pet_id               INT8 REFERENCES pet (id),
    owner_id             INT8 REFERENCES owner (id),
    pet_diet             TEXT,
    health_and_condition TEXT,
    behavioral_changes   TEXT,
    photo_id             INT8 REFERENCES photo (id),
    is_correct           BOOLEAN
);

-- changeSet nadillustrator:7
-- PETS
-- PETS
insert into pet(name, date_of_adoption)
values ('Fly', '2023-01-08');

insert into pet(name, date_of_adoption)
values ('Good boy', '2023-01-03');

insert into pet(name, date_of_adoption)
values ('Nike', '2023-01-05');

insert into pet(name, date_of_adoption)
values ('Gray', '2022-12-08');

insert into pet(name, date_of_adoption)
values ('Fil', '2020-01-04');

insert into pet(name, date_of_adoption)
values ('Crazy', '2023-01-11');

insert into pet(name, date_of_adoption)
values ('Boom', '2023-01-10');

insert into pet(name, date_of_adoption)
values ('Pit', '2023-01-11');

insert into pet(name, date_of_adoption)
values ('Claus', '2023-01-03');

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

-- changeSet evnag:8
ALTER TABLE pet ADD pet_type VARCHAR(255);

-- changeSet evnag:9
-- Set PET TYPES
UPDATE pet SET pet_type = 'dog' WHERE id = '1';
UPDATE pet SET pet_type = 'dog' WHERE id = '2';
UPDATE pet SET pet_type = 'dog' WHERE id = '3';
UPDATE pet SET pet_type = 'cat' WHERE id = '4';
UPDATE pet SET pet_type = 'cat' WHERE id = '5';
UPDATE pet SET pet_type = 'cat' WHERE id = '6';
UPDATE pet SET pet_type = 'dog' WHERE id = '7';
UPDATE pet SET pet_type = 'dog' WHERE id = '8';
UPDATE pet SET pet_type = 'cat' WHERE id = '9';
