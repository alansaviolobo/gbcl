drop database if exists goabottling;
create database goabottling;
use goabottling;
grant all privileges on goabottling.* to 'goabottling'@'%' identified by 'goabottling';

create table warehouse_productivity 
( 
	id int unsigned not null, 
	date date not null, 
	cases_per_employee_hour float unsigned not null,
	primary key (id),
	unique key (date)
);

create table production_productivity(
    id int unsigned not null,
    date date not null,
    gross_production float not null,
    cases_per_employee_hour float not null,
    primary key (id),
    unique key (date)
);

create table line_productivity(
    id int unsigned not null,
    date date not null,
    line_productivity float not null,
    primary key (id),
    unique key (date)
);

create table total_paid_hours(
    id int unsigned not null,
    date date not null,
    total_paid_hours int not null,
    primary key (id),
    unique key (date)
);

create table filler_downtime(
    id int unsigned not null,
    date date not null,
    filler_downtime float not null,
    primary key (id),
    unique key (date)
);

create table cases_not_available_to_load(
    id int unsigned not null,
    date date not null,
    cases_not_available_to_load float not null,
    primary key (id),
    unique key (date)
);

create table warehouse_breakages(
    id int unsigned not null,
    date date not null,
    warehouse_breakages float not null,
    primary key (id),
    unique key (date)
);

create table finished_goods_shrinkage(
    id int unsigned not null,
    date date not null,
    finished_goods_shrinkage float not null,
    primary key (id),
    unique key (date)
);

create table injury_frequency(
    id int unsigned not null,
    date date not null,
    injury_frequency float not null,
    primary key (id),
    unique key (date)
);

create table lost_time(
    id int unsigned not null,
    date date not null,
    lost_time float not null,
    primary key (id),
    unique key (date)
);

create table forecast_accuracy(
    id int unsigned not null,
    date date not null,
    forecast_accuracy float not null,
    good_forecast float not null,
    primary key (id),
    unique key (date)
);

create table ingredient_yield_loss(
    id int unsigned not null,
    date date not null,
    ingredient_yield_loss float not null,
    primary key (id),
    unique key (date)
);

create table yeast_log(
    id int unsigned not null,
    date date not null,
    yeast_log float not null,
    primary key (id),
    unique key (date)
);

create table furnace_oil(
    id int unsigned not null,
    date date not null,
    furnace_oil float not null,
    primary key (id),
    unique key (date)
);

create table water(
    id int unsigned not null,
    date date not null,
    water float not null,
    primary key (id),
    unique key (date)
);

create table power(
    id int unsigned not null,
    date date not null,
    power float not null,
    primary key (id),
    unique key (date)
);
