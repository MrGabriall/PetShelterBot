-- liquibase formatted sql

-- changeSet nadillustrator:1
create table pet (
    id SERIAL,
    name TEXT not null ,
    date_of_adoption DATE not null
);

-- changeSet nadillustrator:2
create table photo(
    id      SERIAL,
    file_id TEXT not null
);

-- changeSet nadillustrator:3
create table report(
    id SERIAL,
    incoming_report_time TIMESTAMP not null,
    pet_id INT8,
    user_id INT8,
    pet_diet TEXT,
    health_and_condition TEXT,
    behavioral_changes TEXT,
    photo_id INT8
);

-- changeSet nadillustrator:4
create table owner(
    id SERIAL,
    user_id INT8,
    last_name TEXT,
    first_name TEXT,
    passport_number INT4,
    number_of_report_days INT4,
    volunteer_id INT8
);

-- changeSet nadillustrator:5
create table volunteer(
                      id SERIAL,
                      user_id INT8,
                      last_name TEXT,
                      first_name TEXT
);


-- changeSet nadillustrator:6
alter table owner rename column passport_number to phone_number;

-- changeSet nadillustrator:7
alter table owner add column pet_id INT8;

-- changeSet nadillustrator:8
insert into owner(user_id, last_name, first_name, phone_number, number_of_report_days, volunteer_id, pet_id)
values (185364843, 'Popova', 'Nadia', 322223222, 30, 322232232, 1);

-- changeSet nadillustrator:9
insert into pet(name, date_of_adoption)
values ('Fly', '2023-01-08 21:30:00.000000');
