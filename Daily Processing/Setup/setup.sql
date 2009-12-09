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

------------------------------------------------------------------------------------------------------------------------
create table warehouse_breakages(
    id int unsigned not null,
    date date not null,
    total_rgb float not null,
    primary key (id),
    unique key (date)
);

Create table production_productivity (
date date not null,
gross_production int not null,
hours_Paid int not null,
primary key (date));

create table product_mix (
id int unsigned not null auto_increment,
name varchar(255) not null
primary key (id));

create table warehouse_productivity (
	supervisor_hours int unsigned not null,
	supervisor_count int unsigned not null,
	operator_hours int unsigned not null,
	operator_count int unsigned not null,
	driver_hours int unsigned not null,
	driver_count int unsigned not null,
	loader_hours int unsigned not null,
	loader_count int unsigned not null,
	cases_loaded int unsigned not null,
);
Create table Lineproductivity_fillerdowntime (
date date not null,
flavour_pack int unsigned not null,
filler_down_time_min int unsigned not null,
run_time_min int unsigned not null,
run_time_hour int unsigned not null,
theo_gross_production int unsigned not null,
actual_gross_production int unsigned not null,
operational_downtime_min int unsigned not null,
operational_downtime_hour int unsigned not null,
primary key  
foriegn key (flavour_pack) references product_mix(id)
on delete restrict on update cascade);

create table total_paid_hours (
date date not null,
shift enum ( "I", "II","III","G") not null,
production_others int unsigned not null,
casual_others int unsigned not null,
warehouse int unsigned not null,
admin_mngt int unsigned not null,
garderner int unsigned not null,
sanitation int unsigned not nul,
sellwell_palatizing int unsigned not null,
security int unsigned not null,
casuals int unsigned not null,
total int unsigned not null,
primary key (date,shift) );


create table finished_goods_shrinkage (
date date not null,
product int unsigned not null,
shrink_bottles int unsigned not null,
shrink_liquid int unsigned not null,
standard_cost_bottles int unsigned not null,
standard_cost_liquid int unsinged not null,
warehouse_shrinkage_amount int unsigned not null,
shipment_transfer_cost int unsigned not null
primary key 

);



create table warehouse_breakage(
date date not null,
product int unsigned not null,
breakage_bottles int unsigned not null,
out_of_code int unsigned not null,
reworked_product int unsigned not null,
net_breakage int unsigned not null,
standard_cost_finished_goods int unsigned not null,
warehouse_breakage_amount int unsigned not null,

Primarykey(product),

foriegn key (product) references product_mix(id)
on delete restrict on update cascade);


create table packaging_yield_loss (
date date not null,
product_code int unsigned not null,
cases_loaded int unsigned not null,
dirty_cases int unsigned not null,
other_brand int unsigned not null,
extra_empties int unsigned not null,
reported_usage int unsigned not null,
yield_loss int unsigned not null,
std_cost_raw_material int unsigned not null,
loss int unsigned not null,
loss int unsigned not null,

primarykey

foriegnkey(product_code) references product_mix(id)
on delete restrict on update cascade);
// product names and product ids.

);

create table raw_material_waste (
date date not null;
item_description int unsigned not null,
Product int unsigned not null,
unit_of_measure int unsigned not null,
usage int unsigned not null,
wip_change int unsigned not null,
agp_consumption int unsigned not null,
waste int unsigned not null,
std_cost_of_rawmaterial int unsigned not null,
waste int unsigned not null,

Primarykey(item_description,product)
foriegnkey(product) references product_mix(id)
on delete restrict on update cascade );
 
create table finished_goods_waste (






NOTE : primary key for filler downtime... can we have two runs of same flavour on same day.
	