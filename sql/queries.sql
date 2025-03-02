-- select table_name from information_schema.tables where table_schema = 'public'

-- create table persons
-- (
-- id serial primary key,
-- firstName character varying(30),
-- lastName character varying(30),
-- email character varying(30),
-- code integer
-- )

-- DROP TABLE persons, models;

-- create table notes
-- (
-- id serial,
-- createDate timestamp,
-- reference uuid
-- )

-- alter table notes rename to models;

insert into persons values (1, 'testF1', 'testL1', 'testE1', 1);

select * from persons;
