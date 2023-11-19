-- create database "rococo-auth" with owner mysql;

create table if not exists users
(
    id                      binary(16) unique DEFAULT (UUID()),
    username                varchar(50) unique not null,
    password                varchar(255)       not null,
    enabled                 boolean            not null,
    account_non_expired     boolean            not null,
    account_non_locked      boolean            not null,
    credentials_non_expired boolean            not null,
    primary key (id, username)
);

create table if not exists authorities
(
    id        binary(16) DEFAULT (UUID()) primary key,
    user_id   binary(16) DEFAULT (UUID()) not null,
    authority varchar(50) not null,
    foreign key (user_id) references users (id)
);
