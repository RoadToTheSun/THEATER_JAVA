DROP DATABASE IF EXISTS THEATER;
CREATE DATABASE THEATER;

create table IF NOT EXISTS THEATER.COLLECTIVE
(
    DIRECTION    INT auto_increment,
    DESCRIPTION  TEXT,
    EMPLOYEE_NUM INT default 0,
    constraint COLLECTIVE_PK
    primary key (DIRECTION)
    );

create table IF NOT EXISTS THEATER.CUSTOMER
(
    ID    INT          not null,
    LOGIN VARCHAR(50)      not null,
    FIO   VARCHAR(150) not null,
    PHONE VARCHAR(12),
    MAIL  VARCHAR ,
    constraint CUSTOMER_PK
    primary key (ID)
    );

create unique index CUSTOMER_LOGIN_UINDEX
    on CUSTOMER (LOGIN);

create table IF NOT EXISTS THEATER.EMPLOYEE
(
    CODE      INT auto_increment,
    FIO       VARCHAR(150) not null,
    SPECIALTY INT,
    DIRECTION INT,
    constraint EMPLOYEE_PK
    primary key (CODE),
    constraint DIRECTION
    foreign key (DIRECTION) references COLLECTIVE (DIRECTION)
    );

create table IF NOT EXISTS THEATER.SPECTACLE
(
    NAME VARCHAR(100) not null,
    DURATION DOUBLE default 0 not null,
    GENRE VARCHAR(100),
    AGE TINYINT default 0 not null,
    DESCRIPTION TEXT,
    RATING TINYINT default 0 not null,
    constraint SPECTACLE_PK
    primary key (NAME)
    );

create index NAME__INDEX
    on SPECTACLE (NAME);

create table IF NOT EXISTS THEATER.SESSION
(
    ID INT not null,
    SPECTACLE_NAME VARCHAR(100),
    MONTH SMALLINT not null,
    DAY SMALLINT,
    TIME TIME not null,
    constraint SESSION_PK
    primary key (ID),
    constraint SESSION_SPECTACLE_NAME_FK
    foreign key (SPECTACLE_NAME) references SPECTACLE (NAME)
    );

comment on table SESSION is 'spectacle session';

create table IF NOT EXISTS THEATER.PLAYBILL
(
    MONTH TINYINT not null,
    SPECTACLE_NAME VARCHAR(100) not null,
    constraint PLAYBILL_PK
    primary key (MONTH, SPECTACLE_NAME),
    constraint PLAYBILL_SPECTACLE_NAME_FK
    foreign key (SPECTACLE_NAME) references SPECTACLE (NAME)
    );

create table IF NOT EXISTS THEATER.SPECTACLE_PARTICIPANT
(
    EMPLOYEE_CODE INT not null,
    SPECTACLE_NAME VARCHAR(100) not null,
    ROLE VARCHAR(50) not null,
    constraint SPECTACLE_PARTICIPANT_PK
    primary key (EMPLOYEE_CODE, SPECTACLE_NAME),
    constraint EMPLOYEE_CODE
    foreign key (EMPLOYEE_CODE) references EMPLOYEE (CODE),
    constraint SPECTACLE_NAME
    foreign key (SPECTACLE_NAME) references SPECTACLE (NAME)
    );

create unique index SPECTACLE_PARTICIPANT_ROLE_UINDEX
    on SPECTACLE_PARTICIPANT (ROLE);

create table IF NOT EXISTS THEATER.TICKET
(
    NUMBER INT auto_increment,
    SESSION_ID INT not null,
    PLACE INT not null,
    CUSTOMER_ID INT,
    constraint TICKET_PK
    primary key (NUMBER),
    constraint TICKET_CUSTOMER_ID
    foreign key (CUSTOMER_ID) references CUSTOMER (ID),
    constraint TICKET_SESSION_ID
    foreign key (SESSION_ID) references SESSION (ID)
    );

create table IF NOT EXISTS THEATER.TICKET_PRICE
(
    ID INT auto_increment,
    TICKET_NUMBER INT,
    PRICE INT not null,
    DATE_FROM DATE not null,
    DATE_TO DATE not null,
    constraint TICKET_PRICE_PK
    primary key (ID),
    constraint TICKET_PRICE
    foreign key (TICKET_NUMBER) references TICKET (NUMBER)
    );

insert into THEATER.SPECTACLE (NAME, DURATION, GENRE, AGE, DESCRIPTION, RATING)
values  ('Горе от ума', 2.5, '', 12, '', 9),
        ('Король Лир', 2.5, '', 18, '', 10),
        ('Обломов', 3, '', 6, '', 7),
        ('Преступление и наказание', 3.5, '', 16, '', 6),
        ('Руслан и Людмилла', 4.2, '', 16, '', 8);

insert into THEATER.PLAYBILL (MONTH, SPECTACLE_NAME)
values  (1, 'Горе от ума'),
        (1, 'Король Лир'),
        (3, 'Король Лир'),
        (1, 'Обломов'),
        (2, 'Обломов'),
        (1, 'Преступление и наказание'),
        (2, 'Руслан и Людмилла');

insert into THEATER.TICKET (NUMBER, SESSION_ID, PLACE, CUSTOMER_ID, CUR_PRICE)
values  (11, 1, 1, null, 300),
        (21, 2, 1, null, 300),
        (31, 3, 1, null, 300),
        (41, 4, 1, null, 300),
        (51, 5, 1, null, 300),
        (61, 6, 1, null, 300),
        (71, 7, 1, null, 300);


