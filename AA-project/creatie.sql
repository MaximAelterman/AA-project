drop table Reservaties purge;
drop table Momenten purge;
drop table Groepen purge;
drop table Gebruikers purge;
drop table Machines purge;

create table Gebruikers (
	gebruikersnaam varchar2(20) primary key,
	paswoord varchar(20), 
        opleiding varchar(10) 
);
create table Groepen (
	gebruikersnaam varchar2(20) references gebruikers primary key,
	groep varchar2(20) 
);

create table Machines (
	mnr integer primary key,
	mnaam varchar2(20),
	omschrijving varchar2(150),
	mloc varchar2(20),
	opleiding varchar2(20),
        serienr integer,        -- machine serienummer
	aankoopprijs integer,
	huurprijs integer
);

create table Momenten (
	momid  integer primary key,
	mnr   integer,
	strt  integer,
        duur integer,
	datum date,
        FOREIGN KEY (mnr) REFERENCES Machines(mnr) ON DELETE CASCADE
);

create table Reservaties (
	rnr integer primary key,
	gebruikersnaam varchar2(20),
        momid integer,
        FOREIGN KEY (momid) REFERENCES Momenten(momid) ON DELETE CASCADE,
        FOREIGN KEY (gebruikersnaam) REFERENCES Gebruikers(gebruikersnaam) ON DELETE CASCADE
);


-- insert into Gebruikers (gebruikersnaam, groep, opleiding);
insert into Gebruikers values ('hcr', 'hcr', 'EICT');
insert into Gebruikers values ('student', 'student','EICT');
insert into Gebruikers values ('chemie', 'chemie', 'chemie');
insert into Gebruikers values ('extern', 'extern','chemie');

-- insert into Groepen (gebruikersnaam, groep);
insert into Groepen values ('hcr', 'Docent');
insert into Groepen values ('student', 'Student');
insert into Groepen values ('chemie', 'Student');
insert into Groepen values ('extern', 'Extern');

-- insert into Machines (machinenr, naam, omschrijving, locatie, opleiding, serienr, aankoopprijs, huurprijs);
insert into Machines values (1, 'machine 1', 'De eerste machine.', 'A213', 'EICT', 0001, 700, 5);
insert into Machines values (2, 'machine 2', 'De tweede machine.', 'A213', 'EICT', 1234 , 700, 5);
insert into Machines values (3, 'machine 3', 'HP-Unix pc 1.', 'A217', 'EICT', 1478, 800, 6);
insert into Machines values (4, 'machine 4', 'HP-Unix pc 2.', 'A217', 'EICT', 0147, 800, 6);
insert into Machines values (5, 'machine 5', 'chemie pc 1.', 'C108', 'chemie', 1259, 950, 9);
insert into Machines values (6, 'machine 6', 'chemie pc 2.', 'C108', 'chemie', 2314, 900, 8);

-- insert into Momenten (momentID, machinenr, starttijd, duur, datum);
insert into Momenten values ( 1, 2, 11, 1, TO_DATE('27/12/2019', 'DD/MM/YYYY'));
insert into Momenten values ( 2, 2, 12, 2, TO_DATE('27/12/2019', 'DD/MM/YYYY'));
insert into Momenten values ( 3, 2, 14, 2, TO_DATE('27/12/2019', 'DD/MM/YYYY'));
insert into Momenten values ( 4, 3, 11, 3, TO_DATE('28/12/2019', 'DD/MM/YYYY'));
insert into Momenten values ( 5, 3, 15, 1, TO_DATE('30/12/2019', 'DD/MM/YYYY'));

-- insert into Reservaties (ReservatieID, gebruikersnaam, momentID);
insert into Reservaties values (1, 'student',1);
insert into Reservaties values (2, 'hcr',4);