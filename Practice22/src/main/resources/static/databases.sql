create table Departures (
id INTEGER,
type varchar(100),
departureDate varchar(10),
postOffice_id INTEGER
);
create sequence departures_sequence start 1 increment 1;
create table PostOffices (
id int,
name varchar(100),
cityName varchar(100)
);