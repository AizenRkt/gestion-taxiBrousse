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

CREATE TABLE voyage (
    id_voyage SERIAL PRIMARY KEY,
    id_trajet INT REFERENCES trajet(id_trajet),
    id_vehicule INT REFERENCES vehicule(id_vehicule),
    id_chauffeur INT REFERENCES chauffeur(id_chauffeur),
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL
);

CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    id_voyage INT REFERENCES voyage(id_voyage),
    nb_place_reserve INT NOT NULL,
    date_reservation TIMESTAMP NOT NULL,
    total_montant NUMERIC(10, 2) NOT NULL
);

INSERT INTO voyage (
    id_trajet,
    id_vehicule,
    id_chauffeur,
    date_depart,
    date_arrivee
) VALUES
-- Voyage 1 : Antananarivo → Toamasina
(1, 1, 1, '2026-01-10 06:00:00', '2026-01-10 14:30:00'),

-- Voyage 2 : Antananarivo → Mahajanga
(2, 2, 2, '2026-01-11 05:30:00', '2026-01-11 16:00:00'),

-- Voyage 3 : Antsirabe → Fianarantsoa
(1, 1, 3, '2026-01-12 07:00:00', '2026-01-12 13:45:00'),

-- Voyage 4 : Toamasina → Antananarivo (retour)
(1, 3, 1, '2026-01-13 06:30:00', '2026-01-13 15:00:00'),

-- Voyage 5 : Mahajanga → Antananarivo
(2, 2, 2, '2026-01-14 05:00:00', '2026-01-14 16:30:00');
