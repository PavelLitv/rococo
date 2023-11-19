-- create database "rococo-userdata" with owner mysql;

create table if not exists users
(
    id                      binary(16) unique DEFAULT (UUID()),
    username                varchar(50) unique not null,
    firstname               varchar(50),
    lastname                varchar(50),
    avatar                  MEDIUMBLOB,
    primary key (id, username)
);