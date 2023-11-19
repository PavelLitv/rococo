-- create database "rococo-content" with owner mysql;

create table if not exists artist
(
    id        binary(16)   unique DEFAULT (UUID()) primary key,
    name      varchar(50)  not null,
    biography varchar(500) not null,
    photo     MEDIUMBLOB   not null
);

create table if not exists museum
(
    id          binary(16)   unique DEFAULT (UUID()) primary key,
    title       varchar(50)                     not null,
    description varchar(500)                    not null,
    photo       MEDIUMBLOB                      not null
);

create table if not exists painting
(
    id                      binary(16)   unique DEFAULT (UUID()) primary key,
    title                   varchar(100)                         not null,
    description             varchar(500)                         not null,
    content                 MEDIUMBLOB                           not null,
    artist_id               binary(16) DEFAULT (UUID())          not null,
    museum_id               binary(16) DEFAULT (UUID())          not null,
    foreign key (artist_id) references artist (id),
    foreign key (museum_id) references museum (id)
);
