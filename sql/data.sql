drop schema if exists rsvpdb;

create schema if not exists rsvpdb;
use rsvpdb;

create table rsvp (
	rsvp_id int not null auto_increment,
    name varchar(64) not null,
    email varchar(50) not null,
    phone varchar(25),
    confirmation_date date DEFAULT (CURRENT_DATE),
    comments text,

    primary key (rsvp_id)
);

insert into rsvp (name ,email ,phone ,confirmation_date,comments)
values ('jessie','j@hotmail.com','555-56789', '2022-05-19','to bring balloons'),
('guil','g@hotmail.com','555-56789', '2022-05-20','to bring hotpot'),
('levi','levi@hotmail.com','555-56789', '2022-05-21','will arrive at 4pm'),
('fred','fred@hotmail.com','555-12345', '2022-05-21','will arrive at 3pm'),
('fredrick','fredrick@hotmail.com','555-12345', '2022-05-21','will arrive at 3pm'),
('jess','jess@hotmail.com','555-56789', '2022-05-19','to bring balloons');