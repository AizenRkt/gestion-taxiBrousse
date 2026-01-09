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
