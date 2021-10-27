--drop database if exists mercedes;
--create database mercedes;
use mercedes;

create table if not exists names(
	name_id INT auto_increment primary key,
    user_name varchar(50) unique not null
);

create table if not exists surnames(
	surname_id INT auto_increment primary key,
    user_surname varchar(50) unique not null
);

create table if not exists access_types(
	access_type_id INT auto_increment primary key,
    access_type varchar(20) unique not null
);

create table if not exists users(
	id INT auto_increment primary key,
    user_name varchar(50) not null,
    user_surname varchar(50) not null,
    email varchar(50) unique not null,
    password varchar(50) not null,
    access_type varchar(20) not null,
    avatar varchar(100),
    foreign key(user_name) references names(user_name),
    foreign key(user_surname) references surnames(user_surname),
    foreign key(access_type) references access_types(access_type)
);

create table if not exists types(
	type_id INT auto_increment primary key,
    type varchar(50) unique not null
);

create table if not exists allCars(
	allCars_id INT auto_increment primary key,
    allCars_name varchar(50) unique not null
);

create table if not exists cars(
	car_id INT auto_increment primary key,
    name_of_mark  varchar(50) not null,
    price varchar(20) not null,
    power varchar(10) not null,
    acceleration_til_hundred varchar(10) not null,
    consumption varchar(20) not null,
    engine_volume varchar(20) not null,
    tank_volume varchar(20) not null,
    trunk_volume varchar(20) not null,
    max_speed varchar(20) not null,
    image_path varchar(100) not null,
    type varchar(50) not null,
    foreign key(name_of_mark) references allCars(allCars_name),
    foreign key(type) references types(type)
);

create table if not exists minibuses(
	minibus_id INT auto_increment primary key,
    name_of_mark  varchar(50) unique not null,
	price varchar(20) not null,
    image_path varchar(100) not null,
    full_load varchar(20) not null,
    curb_weight varchar(20) not null,
    foreign key(name_of_mark) references allCars(allCars_name)
);

create table if not exists trucks(
	trucks_id INT auto_increment primary key,
    truck_name varchar(50) unique not null,
    foreign key(truck_name) references allCars(allCars_name)
);

create table if not exists services(
	service_id INT auto_increment primary key,
    service varchar(50) unique not null
);

create table if not exists orders(
	orders_id INT auto_increment primary key,
    user_name varchar(50) not null,
    user_surname varchar(50) not null,
    email varchar(50) not null,
    service varchar(50) not null,
    car_name varchar(50) not null,
    price varchar(20) not null,
    phone varchar(20) not null,
    date varchar(20),
    status_in_account varchar(10),--need to make this line a foreign key and create new table with statuses
    foreign key(car_name) references allCars(allCars_name),
    foreign key(service) references services(service),
    foreign key(user_name) references names(user_name),
    foreign key(user_surname) references surnames(user_surname)
);