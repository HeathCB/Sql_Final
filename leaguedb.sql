create database if not exists prosports;

use prosports;

drop table if exists players;
drop table if exists teams;
drop table if exists divisions;
drop table if exists leagues;

create table leagues (
	id int(10) not null auto_increment,
	league_name varchar(50) not null,
	primary key(id)
);

create table divisions (
	id int(10) not null auto_increment,
	division_name varchar(50) not null,
	division_champs int(10),
	league_id int(10) not null,
	foreign key league_id references leagues(id),
	primary key(id)
);

create table teams (
	id int(10) not null auto_increment,
	team_name varchar(50) not null,
	team_champs int(10),
	division_id int(10) not null,
	foreign key division_id references divisions(id),
	primary key(id)
);

create table players (
	id int(10) not null auto_increment,
	player_name varchar(50) not null,
	player_num int (2) not null,
	player_position varchar(50) not null,
	team_id int(10) not null,
	foreign key team_id references teams(id),
	primary key(id)
);


		