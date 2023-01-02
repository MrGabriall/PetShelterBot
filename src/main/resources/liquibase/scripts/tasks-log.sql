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