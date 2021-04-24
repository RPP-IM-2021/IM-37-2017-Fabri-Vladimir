DROP TABLE if exists proizvodjac  cascade;
DROP TABLE if exists proizvod  cascade;
DROP TABLE if exists racun  cascade;
DROP TABLE if exists stavka_racuna cascade;

DROP SEQUENCE if exists proizvodjac_seq;
DROP SEQUENCE if exists proizvod_seq;
DROP SEQUENCE if exists racun_seq;
DROP SEQUENCE if exists stavka_racuna_seq;

Create table proizvodjac(
	id integer not null,
	naziv varchar(50) not null,
	kontakt varchar(100) not null,
	adresa varchar(200) not null
);

Create table racun (
	id integer not null,
	datum date not null,
	nacin_placanja varchar(200)
);

Create table proizvod (
	id integer not null,
	naziv varchar(50) not null,
	proizvodjac integer not null
);

Create table stavka_racuna(
	id integer not null,
	redni_broj integer not null,
	kolicina numeric not null,
	jedinica_mere varchar(50) not null,
	cena numeric not null,
	racun integer not null,
	proizvod integer not null
);

ALTER TABLE proizvodjac ADD CONSTRAINT Pk_proizvodjac PRIMARY KEY(id);
ALTER TABLE racun ADD CONSTRAINT Pk_racun PRIMARY KEY(id);
ALTER TABLE proizvod ADD CONSTRAINT Pk_proizvod PRIMARY KEY(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT Pk_stavka_racuna PRIMARY KEY(id);

ALTER TABLE proizvod ADD CONSTRAINT Fk_proizvod_proizvodjac FOREIGN KEY (proizvodjac) REFERENCES proizvodjac(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT Fk_stavka_racuna_proizvod FOREIGN KEY (proizvod) REFERENCES proizvod(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT Fk_stavka_racuna_racun FOREIGN KEY (racun) REFERENCES racun(id);

CREATE INDEX IDXFK_proizvod_proizvodjac on proizvod (proizvodjac);
CREATE INDEX IDXFK_stavka_racuna_proizvod on stavka_racuna (proizvod);
CREATE INDEX IDXFK_stavka_racuna_racun on stavka_racuna (racun);

CREATE SEQUENCE proizvodjac_seq INCREMENT 1;
CREATE SEQUENCE proizvod_seq INCREMENT 1;
CREATE SEQUENCE racun_seq INCREMENT 1;
CREATE SEQUENCE stavka_racuna_seq INCREMENT 1;