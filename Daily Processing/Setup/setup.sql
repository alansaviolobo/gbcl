drop database if exists goabottling;
create database goabottling;
use goabottling;
grant all privileges on goabottling.* to 'goabottling'@'%' identified by 'goabottling';

create table brix
(
	id int unsigned not null,
	date date not null,
	brix float unsigned not null,
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

create table co2(
    id int unsigned not null,
    date date not null,
    co2 float not null,
    primary key (id),
    unique key (date)
);

create table co2_yield(
    id int unsigned not null,
    date date not null,
    co2_yield float not null,
    primary key (id),
    unique key (date)
);

create table concentrate_yield(
    id int unsigned not null,
    date date not null,
    concentrate_yield float not null,
    primary key (id),
    unique key (date)
);

create table crown_yield(
    id int unsigned not null,
    date date not null,
    crown_yield float not null,
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

create table finished_goods_shrinkage(
    id int unsigned not null,
    date date not null,
    finished_goods_shrinkage float not null,
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

create table furnace_oil(
    id int unsigned not null,
    date date not null,
    furnace_oil float not null,
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

create table injury_frequency(
    id int unsigned not null,
    date date not null,
    injury_frequency float not null,
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

create table lost_time(
    id int unsigned not null,
    date date not null,
    lost_time float not null,
    primary key (id),
    unique key (date)
);

create table material_availability(
    id int unsigned not null,
    date date not null,
    material_availability float not null,
    primary key (id),
    unique key (date)
);

create table packaging_yield_loss(
    id int unsigned not null,
    date date not null,
    packaging_yield_loss float not null,
    primary key (id),
    unique key (date)
);

create table power_beverage(
    id int unsigned not null,
    date date not null,
    power_beverage float not null,
    primary key (id),
    unique key (date)
);

create table production_breakage(
    id int unsigned not null,
    date date not null,
    production_breakage float not null,
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

create table schedule_compliance(
    id int unsigned not null,
    date date not null,
    schedule_compliance float not null,
    primary key (id),
    unique key (date)
);

create table sugar_yield(
    id int unsigned not null,
    date date not null,
    sugar_yield float not null,
    primary key (id),
    unique key (date)
);

create table ta(
    id int unsigned not null,
    date date not null,
    ta float not null,
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

create table total_plant_waste(
    id int unsigned not null,
    date date not null,
    total_plant_waste int not null,
    primary key (id),
    unique key (date)
);

create table warehouse_breakage(
    id int unsigned not null,
    date date not null,
    warehouse_breakage float not null,
    primary key (id),
    unique key (date)
);

create table warehouse_productivity
( 
	id int unsigned not null, 
	date date not null, 
	cases_per_employee_hour float unsigned not null,
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

create table yeast_log(
    id int unsigned not null,
    date date not null,
    yeast_log float not null,
    primary key (id),
    unique key (date)
);