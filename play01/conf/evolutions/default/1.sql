# --- First database schema

# --- !Ups

create table contact (
  id                        bigint not null,
  name                      varchar(255),
  phone                      varchar(255),
  email                      varchar(255),
);


