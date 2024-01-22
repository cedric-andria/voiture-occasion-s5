create database vehiculeoccasion;
create user vehiculeoccasion with password 'vehiculeoccasion';
grant all privileges on database vehiculeoccasion to vehiculeoccasion;
\c vehiculeoccasion vehiculeoccasion;

create table Profil(
    id serial primary key,
    description varchar(100)
);
insert into Profil(description) values ('readonly');
insert into Profil(description) values ('allaccess');

create table Utilisateur(
    id serial primary key,
    nom varchar(100),
    identifiant varchar(100) unique,
    mdp varchar(100),
    id_profil int references Profil(id)
);
insert into Utilisateur(nom, identifiant, mdp, id_profil) values ('Rakoto', 'rakoto@gmail.com', 'rakoto123', 1);
insert into Utilisateur(nom, identifiant, mdp, id_profil) values ('Randria', 'randria@gmail.com', 'randria123', 1);
insert into Utilisateur(nom, identifiant, mdp, id_profil) values ('Rabe', 'rabe@gmail.com', 'rabe123', 1);

create table Marque(
    id serial primary key,
    nom varchar(100)
);
insert into Marque(nom) values ('Toyota');
insert into Marque(nom) values ('Hyundai');
insert into Marque(nom) values ('Haval');
insert into Marque(nom) values ('Chevrolet');
insert into Marque(nom) values ('Mazda');
insert into Marque(nom) values ('Caterpillar');


create table Modele(
    id serial primary key,
    id_marque int references Marque(id),
    nom varchar(100)
);
insert into Modele(id_marque, nom) values (1, 'Yarris');
insert into Modele(id_marque, nom) values (1, 'Corolla');
insert into Modele(id_marque, nom) values (1, 'Mirai');

insert into Modele(id_marque, nom) values (2, 'Ioniq');
insert into Modele(id_marque, nom) values (2, 'Kona');

insert into Modele(id_marque, nom) values (3, 'Dargo');
insert into Modele(id_marque, nom) values (3, 'Jolion');

insert into Modele(id_marque, nom) values (4, 'Alero');
insert into Modele(id_marque, nom) values (4, 'Apache');
insert into Modele(id_marque, nom) values (4, 'Avalanche');
insert into Modele(id_marque, nom) values (4, 'Aveo');

insert into Modele(id_marque, nom) values (5, 'Hatchback');
insert into Modele(id_marque, nom) values (5, 'Sedan');
insert into Modele(id_marque, nom) values (5, 'CX-5');
insert into Modele(id_marque, nom) values (5, 'CX-30');
insert into Modele(id_marque, nom) values (5, 'MX-5');

insert into Modele(id_marque, nom) values (6, 'Bergerat Monnoyeur');

create table Categorie(
    id serial primary key,
    nom varchar(100)
);
insert into Categorie(nom) values ('lourd');
insert into Categorie(nom) values ('chantier');
insert into Categorie(nom) values ('leger');

create table Voiture(
    id serial primary key,
    id_vendeur int references Utilisateur(id),
    id_modele int references Modele(id),
    annee_sortie int check (annee_sortie >= 1803 and annee_sortie <= (EXTRACT(YEAR from current_date))),
    id_categorie int references Categorie(id)
);
insert into Voiture(id_vendeur, id_modele, annee_sortie, id_categorie) values (2, 2, 2014, 3);
insert into Voiture(id_vendeur, id_modele, annee_sortie, id_categorie) values (2, 10, 2018, 3);
insert into Voiture(id_vendeur, id_modele, annee_sortie, id_categorie) values (3, 14, 2020, 1);
insert into Voiture(id_vendeur, id_modele, annee_sortie, id_categorie) values (3, 12, 2021, 1);
insert into Voiture(id_vendeur, id_modele, annee_sortie, id_categorie) values (3, 17, 2015, 1);

-- insert into vehicule(date_fabrication, couleur, idModele) values (now(), 'lavande', 2);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2021-11-23', 'noir', 3);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2020-08-08', 'bleu', 4);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2020-12-27', 'blanc', 5);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2019-04-03', 'vert', 6);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2022-06-17', 'jaune', 7);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2023-01-19', 'bleu', 8);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2021-02-18', 'noir', 9);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2021-05-14', 'bleu', 10);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2021-06-11', 'rouge', 11);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2022-06-25', 'orange', 12);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2022-11-27', 'noir', 13);
-- insert into vehicule(date_fabrication, couleur, idModele) values ('2018-02-02', 'noir', 14);

create table Photo(
    id serial primary key,
    id_voiture int references Voiture(id),
    chemin varchar(100)
);
insert into Photo(id_voiture, chemin) values (1, 'user/2/voiture/1/black1.jpg');
insert into Photo(id_voiture, chemin) values (1, 'user/2/voiture/1/black2.jpg');
insert into Photo(id_voiture, chemin) values (1, 'user/2/voiture/1/black3.jpg');
insert into Photo(id_voiture, chemin) values (2, 'user/2/voiture/2/red1.jpg');
insert into Photo(id_voiture, chemin) values (2, 'user/2/voiture/2/red2.jpg');
insert into Photo(id_voiture, chemin) values (2, 'user/2/voiture/2/red3.jpg');
-- insert into Photo(id_voiture, chemin) values (3, 'user/3/voiture/3/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (3, 'user/3/voiture/3/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (3, 'user/3/voiture/3/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (4, 'user/3/voiture/1/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (4, 'user/3/voiture/1/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (4, 'user/3/voiture/1/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (5, 'user/3/voiture/1/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (5, 'user/3/voiture/1/black1.jpg');
-- insert into Photo(id_voiture, chemin) values (5, 'user/3/voiture/1/black1.jpg');

create table Annonce(
    id serial primary key,
    id_voiture int references Voiture(id),
    prix double precision check (prix > 0),
    description varchar(100) CHECK (LENGTH(description) >= 50),
    etat int check (etat >= 0),
    date_publication date
);

create table Favori (
    id serial primary key,
    id_annonce int references Annonce(id),
    id_utilisateur int references Utilisateur(id)
);

create table Photovoiture (
    id serial primary key, 
    id_photo int references Photo(id),
    id_annonce int references Annonce(id)
);

create table HistoriqueAnnonce (
    id serial primary key,
    id_annonce int references Annonce(id),
    date_operation date,
    nouvel_etat int
);

CREATE OR REPLACE FUNCTION insert_historique_annonce()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO HistoriqueAnnonce (id_annonce, date_operation, nouvel_etat)
    VALUES (NEW.id, NEW.date_publication, NEW.etat);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER annonce_insert_trigger
AFTER INSERT ON Annonce
FOR EACH ROW
EXECUTE FUNCTION insert_historique_annonce();

--etat : 0 = publiee, 10 : validee, 20 : vendue
insert into annonce (id_voiture, prix, description, etat, date_publication) values (1, 150000000, 'Voiture tena tsara, description exemple for id_voiture 1', 10, current_date);
insert into annonce (id_voiture, prix, description, etat, date_publication) values (2, 480000000, 'Voiture haut de gamme, , description exemple for id_voiture 2', 10, current_date);
-- insert into annonce (id_voiture, prix, description, etat, date_publication) values ();
-- insert into annonce (id_voiture, prix, description, etat, date_publication) values ();
-- insert into annonce (id_voiture, prix, description, etat, date_publication) values ();

insert into Photovoiture (id_photo, id_annonce) values (1, 1);
insert into Photovoiture (id_photo, id_annonce) values (2, 1);
insert into Photovoiture (id_photo, id_annonce) values (3, 1);

insert into Photovoiture (id_photo, id_annonce) values (4, 2);
insert into Photovoiture (id_photo, id_annonce) values (5, 2);
insert into Photovoiture (id_photo, id_annonce) values (6, 2);


insert into Favori(id_annonce, id_utilisateur) values (1, 3);


CREATE OR REPLACE FUNCTION check_date_operation()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.date_operation <= (SELECT date_publication FROM Annonce WHERE id = NEW.id_annonce) THEN
        RAISE EXCEPTION 'date_operation must be greater or equal than date_publication';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_date_operation_trigger
BEFORE INSERT OR UPDATE ON HistoriqueAnnonce
FOR EACH ROW
EXECUTE FUNCTION check_date_operation();


insert into HistoriqueAnnonce (id_annonce, date_operation, nouvel_etat) values (2, '24-10-2024', 20);


create table Vente (
    id serial primary key,
    id_annonce int references Annonce(id),
    id_client int references Utilisateur(id),
    prix double precision check (prix > 0),
    date_vente date
);
CREATE OR REPLACE FUNCTION check_date_vente()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.date_vente <= (SELECT date_vente FROM Annonce WHERE id = NEW.id_annonce) THEN
        RAISE EXCEPTION 'date_vente must be greater than date_publication';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_date_vente_trigger
BEFORE INSERT OR UPDATE ON Vente
FOR EACH ROW
EXECUTE FUNCTION check_date_vente();

-- insert into Vente (id_annonce, id_client, prix, date_vente) values ();