
		create database if not exists prosports;

use prosports;

drop table if exists players;
drop table if exists teams;
drop table if exists divisions;
drop table if exists leagues;

create table leagues (
	leagueId int(10) not null auto_increment,
	league_name varchar(50) not null,
	primary key(leagueId)
);

create table divisions (
	divisionId int(10) not null auto_increment,
	division_name varchar(50) not null,
	division_champs int(10),
	leagueId int(10) not null,
	foreign key (leagueId) references leagues(leagueId),
	primary key(divisionid)
);

create table teams (
	teamId int(10) not null auto_increment,
	team_name varchar(50) not null,
	team_champs int(10),
	divisionId int(10) not null,
	foreign key (divisionId) references divisions(divisionId),
	primary key(teamId)
);

create table players (
	playerId int(10) not null auto_increment,
	player_name varchar(50) not null,
	player_num int (2) not null,
	player_position varchar(50) not null,
	teamId int(10) not null,
	foreign key (teamId) references teams(teamId),
	primary key(playerId)
);
