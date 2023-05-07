create table usuarios
(
    id       bigint not null,
    login    varchar(255),
    password varchar(255),
    primary key (id)
);