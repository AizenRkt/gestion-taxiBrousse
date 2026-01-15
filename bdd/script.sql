-- itineraire
CREATE TABLE arret (
    id_arret SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE trajet (
    id_trajet SERIAL PRIMARY KEY,
    code_trajet VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE trajet_arret (
    id_trajet_arret SERIAL PRIMARY KEY,
    id_trajet INT REFERENCES trajet(id_trajet),
    id_arret INT REFERENCES arret(id_arret),
    ordre INT NOT NULL
);

CREATE TABLE trajet_tarif(
    id_trajet_tarif SERIAL PRIMARY KEY,
    id_trajet INT REFERENCES trajet(id_trajet),
    montant DECIMAL(10, 2) NOT NULL,
    date_tarif DATE NOT NULL
);

-- chauffeur
CREATE TABLE chauffeur (
    id_chauffeur SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_de_naissance DATE
);

CREATE TYPE chauffeur_status_enum AS ENUM ('actif', 'inactif');
CREATE TABLE chauffeur_status (
    id_chauffeur_status SERIAL PRIMARY KEY,
    id_chauffeur INT REFERENCES chauffeur(id_chauffeur),
    status chauffeur_status_enum NOT NULL,
    date_status TIMESTAMP NOT NULL
);

-- vehicule 
CREATE TABLE vehicule (
    id_vehicule SERIAL PRIMARY KEY,
    immatriculation VARCHAR(20) NOT NULL,
    marque VARCHAR(100),
    capacite_passager INT,
    date_mise_en_service DATE
);
CREATE TYPE vehicule_status_enum AS ENUM ('actif', 'inactif');
CREATE TABLE vehicule_status (
    id_vehicule_status SERIAL PRIMARY KEY,
    id_vehicule INT REFERENCES vehicule(id_vehicule),
    status vehicule_status_enum NOT NULL,
    date_status TIMESTAMP NOT NULL
);

-- voyage 
CREATE TABLE voyage (
    id_voyage SERIAL PRIMARY KEY,
    id_trajet INT REFERENCES trajet(id_trajet),
    id_vehicule INT REFERENCES vehicule(id_vehicule),
    id_chauffeur INT REFERENCES chauffeur(id_chauffeur),
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL
);

-- partie client 
-- CREATE TABLE client (
--     id_client SERIAL PRIMARY KEY,
--     nom VARCHAR(100) NOT NULL,
--     telephone VARCHAR(20)
-- );

CREATE TABLE trajet (
    id_trajet SERIAL PRIMARY KEY,
    code_trajet VARCHAR(50) NOT NULL,
    description TEXT
);

-- CREATE TABLE voyage (
--     id_voyage SERIAL PRIMARY KEY,
--     id_trajet INT REFERENCES trajet(id_trajet),
--     id_vehicule INT REFERENCES vehicule(id_vehicule),
--     id_chauffeur INT REFERENCES chauffeur(id_chauffeur),
--     date_depart TIMESTAMP NOT NULL,
--     date_arrivee TIMESTAMP NOT NULL
-- );

CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    client_nom VARCHAR(100) NOT NULL,
    client_tel VARCHAR(20),
    id_voyage INT REFERENCES voyage(id_voyage),
    nombre_places INT NOT NULL,
    total_payer DECIMAL(10, 2) NOT NULL,
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table place (
    id_place SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL
);

create table place_type(
    id_place_type SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

create table vehicule_place(
    id_vehicule_place SERIAL PRIMARY KEY,
    id_vehicule INT REFERENCES vehicule(id_vehicule),
    id_place INT REFERENCES place(id_place),
    id_place_type INT REFERENCES place_type(id_place_type)
);

create table tarif_place_type(
    id_tarif_place_type SERIAL PRIMARY KEY,
    id_place_type INT REFERENCES place_type(id_place_type),
    montant DECIMAL(10,2) NOT NULL,
    date_tarif DATE NOT NULL
);

INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type) VALUES
(5, 1, 1),
(5, 2, 1),
(5, 3, 2),
(5, 4, 1),
(5, 5, 1),
(5, 6, 2),
(5, 7, 1),
(5, 8, 2),
(5, 9, 1),
(5, 10, 1),
(5, 11, 2),
(5, 12, 2);
(5, 12, 2);
(5, 12, 2);
(5, 12, 2);
(5, 12, 2);
(5, 12, 2);
(5, 12, 2);

-- 6 places Premium
DO $$
DECLARE i INT;
BEGIN
  FOR i IN 1..6 LOOP
    INSERT INTO place (code) VALUES ('P' || i);
    INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
    VALUES (5, currval('place_id_place_seq'), 2);
  END LOOP;
END $$;

-- 10 places Standard
DO $$
DECLARE i INT;
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO place (code) VALUES ('S' || i);
    INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
    VALUES (5, currval('place_id_place_seq'), 1);
  END LOOP;
END $$;

-- 2 places VIP
DO $$
DECLARE i INT;
BEGIN
  FOR i IN 1..2 LOOP
    INSERT INTO place (code) VALUES ('V' || i);
    INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
    VALUES (5, currval('place_id_place_seq'), 3);
  END LOOP;
END $$;

UPDATE tarif_place_type
SET montant = 90000.00,
    date_tarif = '2026-01-20'
WHERE id_tarif_place_type = 1;
