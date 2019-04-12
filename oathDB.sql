create database if not exists oath;

use oath;

drop table if exists loot_bank;
drop table if exists loot_table_weapons;
drop table if exists loot_table_armor;
drop table if exists characters;



create table loot_table_weapons(
id int(11) not null auto_increment,
weapon varchar(30) not null,
style varchar(30) not null,
primary key(id)
);

create table loot_table_armor(
id int(11) not null auto_increment,
armor_type varchar(30) not null,
body_slot varchar(30) not null,
primary key(id)
);

create table characters(
id int(11) not null auto_increment,
name varchar(30) not null,
race varchar(30) not null,
class varchar(30) not null,
primary key(id)
);

create table loot_bank(
id int(11) not null auto_increment,
weapon_id int(11),
armor_id int(11),
character_id int(11),
quality varchar(30) not null,
item varchar(30) not null,
plus_stat varchar(30) not null,
primary key(id),
foreign key(weapon_id) references loot_table_weapons(id),
foreign key(armor_id) references loot_table_armor(id),
foreign key(character_id) references characters(id)
);