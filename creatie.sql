drop table Groepen purge;
drop table Reservaties purge;
drop table Gebruikers purge;
drop table Machines purge;

create table Machines (
	mnr int primary key,
	mnaam varchar2(20),
	omschrijving varchar2(120),
	mloc varchar2(20),
	opleiding varchar2(20),
	aankoopprijs float,
	huurprijs float
);

create table Gebruikers (
	gebruikersnaam varchar2(20) primary key,
	paswoord varchar(20), 
        opleiding varchar(10) 
);

create table Reservaties (
	rnr int primary key,
	mnr int references machines,
	gebruikersnaam varchar2(20) references gebruikers,
	datum date,
	uur int,
	duur int
);

create table Groepen (
	gebruikersnaam varchar2(20) references gebruikers primary key,
	groep varchar2(20) 
);

insert into Gebruikers values ('admin','admin','ALL');
insert into Gebruikers values ('hcr', 'hcr', 'EICT');
insert into Gebruikers values ('test', 'test', 'EICT');
insert into Gebruikers values ('student', 'student','EICT');
insert into Gebruikers values ('chemie', 'chemie', 'chemie');
insert into Gebruikers values ('extern', 'extern','Bouw');

insert into Groepen values ('admin', 'Docent');
insert into Groepen values ('hcr', 'Docent');
insert into Groepen values ('test', 'Student');
insert into Groepen values ('student', 'Student');
insert into Groepen values ('chemie', 'Student');
insert into Groepen values ('extern', 'Extern');

insert into Machines values (1, 'machine1', 'De eerste machine.', 'A213', 'EICT', 850.00, 5.00);
insert into Machines values (2, 'machine2', 'De tweede machine.', 'A213', 'EICT', 800.00, 4.00);