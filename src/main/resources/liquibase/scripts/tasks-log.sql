-- liquibase formatted sql

-- changeSet nadillustrator:1
create table pet
(
    id               BIGSERIAL NOT NULL PRIMARY KEY,
    name             TEXT      not null,
    date_of_adoption DATE      not null
);

-- changeSet nadillustrator:2
create table photo
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    file_id TEXT      not null
);

-- changeSet nadillustrator:3
create table volunteer
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    chat_id    INT8,
    last_name  TEXT,
    first_name TEXT
);

-- changeSet nadillustrator:4
create table user_state
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    chat_id INT8,
    state   TEXT
);

-- changeSet nadillustrator:5
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
create table report
(
    id                   BIGSERIAL NOT NULL PRIMARY KEY,
    incoming_report_time TIMESTAMP not null,
    pet_id               INT8 REFERENCES pet (id),
    owner_id             INT8 REFERENCES owner (id),
    pet_diet             TEXT,
    health_and_condition TEXT,
    behavioral_changes   TEXT,
    photo_id             INT8 REFERENCES photo (id),
    is_correct           BOOLEAN
);