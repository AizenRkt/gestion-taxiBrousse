-- itinéraire
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

-- passager
CREATE TABLE passager_type (
    id_passager_type SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
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

CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    client_nom VARCHAR(100) NOT NULL,
    client_tel VARCHAR(20),
    id_voyage INT REFERENCES voyage(id_voyage),
    nombre_places INT NOT NULL,
    total_payer DECIMAL(10, 2) NOT NULL,
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reservation_detail (
    id_reservation_detail SERIAL PRIMARY KEY,
    id_reservation INT REFERENCES reservation(id_reservation),
    id_place_type INT REFERENCES place_type(id_place_type),
    id_passager_type INT REFERENCES passager_type(id_passager_type),
    prix_applique DECIMAL(10,2) NOT NULL,   
    nombre_places INT NOT NULL
);

-- remise
CREATE TABLE remise_type (
    id_remise_type SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE remise (
    id_remise SERIAL PRIMARY KEY,
    id_type_remise INT REFERENCES remise_type(id_remise_type),
    libelle VARCHAR(100),
    valeur DECIMAL(10,2),
    date_debut DATE,
    date_fin DATE
); 

CREATE TABLE remise_condition (
    id_remise_condition SERIAL PRIMARY KEY, 
    id_remise INT REFERENCES remise(id_remise),
    champ VARCHAR(50),      -- type_passager, place_type, trajet, voyage
    operateur VARCHAR(10), -- =, IN
    valeur VARCHAR(100)    -- enfant, economique, 1
);

create table societe(
    id_societe SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

create table publicite(
    id_publicite SERIAL PRIMARY KEY,
    id_societe INT REFERENCES societe(id_societe),
    code VARCHAR(100)  NOT NULL,
   description TEXT,
   duree INT NOT NULL
);

create table diffusion_publicite_voyage(
    id_publicite_diffusion SERIAL PRIMARY KEY,
    id_publicite INT REFERENCES publicite(id_publicite),
    id_voyage INT REFERENCES voyage(id_voyage),
    date_diffusion TIMESTAMP NOT NULL
);

create table tarif_publicite(
    id_tarif_publicite SERIAL PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    date_modif DATE NOT NULL
);