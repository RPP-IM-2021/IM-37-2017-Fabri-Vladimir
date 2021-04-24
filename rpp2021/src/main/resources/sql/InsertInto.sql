insert into racun (id,datum,nacin_placanja)
values(nextval('racun_seq'),to_date('08.04.2021.', 'dd.mm.yyyy.'), 'kes'); 
insert into racun (id,datum,nacin_placanja)
values(nextval('racun_seq'),to_date('02.04.2021.', 'dd.mm.yyyy.'), 'cek'); 
insert into racun (id,datum,nacin_placanja)
values(nextval('racun_seq'),to_date('01.04.2021.', 'dd.mm.yyyy.'), 'kartica');

insert into proizvodjac (id, naziv, kontakt, adresa)
values (nextval('proizvodjac_seq'), 'Hewlett-Packard', '381 11 2055 800', 'Omladinskih brigada 90b Beograd' );
insert into proizvodjac (id, naziv, kontakt, adresa)
values (nextval('proizvodjac_seq'), 'Lenovo', '381 11 6558164', 'Milentija Popovica 5a Beograd' );
insert into proizvodjac (id, naziv, kontakt, adresa)
values (nextval('proizvodjac_seq'), 'ASUS', '381 11 4300328', 'Omladinskih brigada 90b Beograd' );

insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'ASUS TUF Gaming 505GX',3);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'ASUS Vivobook 15',3);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'ASUS Zenbook flip 14',3);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'HP Pavilion 14',1);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'HP Notebook 14',1);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'HP Omen 15',1);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'Lenovo Legion Y530',2);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'Lenovo IdeaPad 3',2);
insert into proizvod(id,naziv,proizvodjac)
values (nextval('proizvod_seq'),'Lenovo Yoga slim 7',2);

insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),1,1,'komad',100000,1,1);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),2,1,'komad',70000,1,4);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),3,1,'komad',130000,1,7);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),1,1,'komad',60000,2,2);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),2,1,'komad',50000,2,5);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),3,1,'komad',90000,2,8);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),1,1,'komad',150000,3,3);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),2,1,'komad',200000,3,6);
insert into stavka_racuna(id,redni_broj,kolicina,jedinica_mere,cena,racun,proizvod)
values(nextval('stavka_racuna_seq'),3,1,'komad',85000,3,9);