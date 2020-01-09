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


-- insert into Gebruikers (gebruikersnaam, paswoord, opleiding);
insert into Gebruikers values ('hcr', 'hcr', 'EICT');
insert into Gebruikers values ('student', 'student','EICT');
insert into Gebruikers values ('chemie', 'chemie', 'chemie');
insert into Gebruikers values ('extern', 'extern','chemie');

insert into Gebruikers values ('jve', 'joost', 'EICT');
insert into Gebruikers values ('avh', 'ann', 'EICT');
insert into Gebruikers values ('maxim', 'maxim','EICT');
insert into Gebruikers values ('jeroen', 'jeroen','chemie');

insert into Gebruikers values ('gast', 'gast','EICT');

-- insert into Groepen (gebruikersnaam, groep);
-- docent
insert into Groepen values ('hcr', 'Docent');
insert into Groepen values ('jve', 'Docent');
insert into Groepen values ('avh', 'Docent');
insert into Groepen values ('chemie', 'Docent');

-- student
insert into Groepen values ('student', 'Student');
insert into Groepen values ('jeroen', 'Student');
insert into Groepen values ('maxim', 'Student');
-- extern
insert into Groepen values ('gast', 'Extern');
insert into Groepen values ('extern', 'Extern');

-- insert into Machines (machinenr, naam, omschrijving, locatie, opleiding, serienr, aankoopprijs, huurprijs);
insert into Machines values (1, 'Laurel 1', 'De eerste machine.', 'A213', 'EICT', 0001, 700, 5);
insert into Machines values (2, 'Laurel 2', 'De tweede machine.', 'A213', 'EICT', 1234 , 700, 5);
insert into Machines values (3, 'Chaplin 1', 'HP-Unix pc 1.', 'A217', 'EICT', 1478, 800, 6);
insert into Machines values (4, 'Chaplin 2', 'HP-Unix pc 2.', 'A217', 'EICT', 0147, 800, 6);
insert into Machines values (5, 'Oxygen 1', 'chemie pc 1.', 'C108', 'Chemie', 1259, 950, 9);
insert into Machines values (6, 'Oxygen 2', 'chemie pc 2.', 'C108', 'Chemie', 2314, 900, 8);

-- insert into Momenten (momentID, machinenr, starttijd, duur, datum);
insert into Momenten values ( 1, 1, 9, 3, TO_DATE('16/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 2, 1, 13, 2, TO_DATE('16/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 3, 1, 16, 2, TO_DATE('16/01/2020', 'DD/MM/YYYY'));

insert into Momenten values ( 4, 2, 10, 4, TO_DATE('18/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 5, 2, 9, 3, TO_DATE('18/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 6, 2, 15, 1, TO_DATE('19/01/2020', 'DD/MM/YYYY'));

insert into Momenten values ( 7, 3, 15, 2, TO_DATE('17/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 8, 3, 11, 3, TO_DATE('17/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 9, 3, 13, 3, TO_DATE('18/01/2020', 'DD/MM/YYYY'));

insert into Momenten values ( 10, 4, 8, 2, TO_DATE('17/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 11, 4, 10, 3, TO_DATE('18/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 12, 4, 16, 2, TO_DATE('18/01/2020', 'DD/MM/YYYY'));

insert into Momenten values ( 13, 5, 10, 2, TO_DATE('19/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 14, 5, 9, 3, TO_DATE('20/01/2020', 'DD/MM/YYYY'));

insert into Momenten values ( 15, 6, 11, 2, TO_DATE('19/01/2020', 'DD/MM/YYYY'));
insert into Momenten values ( 16, 6, 11, 2, TO_DATE('20/01/2020', 'DD/MM/YYYY'));



-- insert into Reservaties (ReservatieID, gebruikersnaam, momentID);
insert into Reservaties values (1, 'student', 1);
insert into Reservaties values (2, 'hcr', 4);
insert into Reservaties values (3, 'jeroen', 8);
insert into Reservaties values (4, 'maxim', 11);
insert into Reservaties values (5, 'jve', 6);
insert into Reservaties values (6, 'extern', 13);
insert into Reservaties values (7, 'gast', 3);