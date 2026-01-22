INSERT INTO arret (nom) VALUES('Antananarivo');
INSERT INTO arret (nom) VALUES('Tamatave');
INSERT INTO arret (nom) VALUES('Majunga');
INSERT INTO arret (nom) VALUES('Fianaratsoa');
INSERT INTO arret (nom) VALUES('Diego');
INSERT INTO arret (nom) VALUES('Maevatanana');
INSERT INTO arret (nom) VALUES('Moramanga');
INSERT INTO arret (nom) VALUES('Ambositra');

INSERT INTO vehicule (immatriculation, marque, capacite_passager, date_mise_en_service) VALUES('A001', 'rdn', 15, '2026-01-01');
INSERT INTO vehicule (immatriculation, marque, capacite_passager, date_mise_en_service) VALUES('A002', 'rdn', 21, '2026-01-01');
INSERT INTO vehicule (immatriculation, marque, capacite_passager, date_mise_en_service) VALUES('A003', 'rdn', 18, '2026-01-01');
INSERT INTO vehicule (immatriculation, marque, capacite_passager, date_mise_en_service) VALUES('A004', 'rdn', 10, '2026-01-01');
INSERT INTO vehicule (immatriculation, marque, capacite_passager, date_mise_en_service) VALUES('A005', 'rdn', 19, '2026-01-01');

INSERT INTO chauffeur (nom, prenom, date_de_naissance) VALUES('Rasoa', 'Jean', '1980-05-15');
INSERT INTO chauffeur (nom, prenom, date_de_naissance) VALUES('Rakoto', 'Paul', '1975-09-20');
INSERT INTO chauffeur (nom, prenom, date_de_naissance) VALUES('Rabe', 'Luc', '1990-12-10');
INSERT INTO chauffeur (nom, prenom, date_de_naissance) VALUES('Ranaivo', 'Marc', '1985-03-25');
INSERT INTO chauffeur (nom, prenom, date_de_naissance) VALUES('Rasolofonirina', 'Sophie', '1992-07-30');

-- données de trajet
INSERT INTO trajet (code_trajet, description) 
VALUES ('T001', 'Trajet principal entre Antananarivo et Tamatave');

INSERT INTO trajet_arret (id_trajet, id_arret, ordre) VALUES (1, 1, 1); -- Antananarivo
INSERT INTO trajet_arret (id_trajet, id_arret, ordre) VALUES (1, 2, 2); -- Tamatave

INSERT INTO trajet_tarif (id_trajet, montant, date_tarif)
VALUES (1, 30000, '2024-01-01');

-- donées de voyage 
INSERT INTO voyage (id_trajet, id_vehicule, id_chauffeur, date_depart, date_arrivee)
VALUES (1, 1, 1, '2024-07-01 08:00:00', '2024-07-01 18:00:00');

INSERT INTO place_type (libelle) VALUES ('économique'), ('premium'), ('VIP');
INSERT INTO remise_type (libelle) VALUES('POURCENTAGE'), ('MONTANT_FIXE');

INSERT INTO passager_type (libelle) VALUES
('adulte'),
('enfant'),
('senior');

INSERT INTO tarif_place_type (id_place_type, montant, date_tarif) VALUES
(1, 50000, '2026-01-01'),  -- eco
(2, 60000, '2026-01-01'),  -- premium
(3, 70000, '2026-01-01');  -- vip

INSERT INTO place (code) VALUES
('P01'), ('P02'), ('P03'), ('P04'), ('P05'),
('P06'), ('P07'), ('P08'), ('P09'), ('P10'),
('P11'), ('P12'), ('P13'), ('P14'), ('P15'),
('P16'), ('P17'), ('P18'), ('P19'), ('P20');

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
(5, 12, 2),
(5, 13, 2),
(5, 14, 2),
(5, 15, 2),
(5, 16, 2),
(5, 17, 2),
(5, 18, 2);

-- données aléa 
-- enfant
INSERT INTO remise (id_type_remise, libelle, valeur, date_debut)
VALUES (2, 'Tarif enfant économique', 40000, '2026-01-01');
INSERT INTO remise_condition (id_remise, champ, operateur, valeur)
VALUES
(1, 'passager_type', '=', 'enfant'),
(1, 'place_type', '=', 'économique');


INSERT INTO remise (id_type_remise, libelle, valeur, date_debut)
VALUES (2, 'Tarif enfant premium', 50000, '2026-01-01');
INSERT INTO remise_condition (id_remise, champ, operateur, valeur)
VALUES
(2, 'passager_type', '=', 'enfant'),
(2, 'place_type', '=', 'premium');


INSERT INTO remise (id_type_remise, libelle, valeur, date_debut)
VALUES (2, 'Tarif enfant VIP', 65000, '2026-01-01');
INSERT INTO remise_condition (id_remise, champ, operateur, valeur)
VALUES
(3, 'passager_type', '=', 'enfant'),
(3, 'place_type', '=', 'VIP');

-- senior 
INSERT INTO remise (id_type_remise, libelle, valeur, date_debut)
VALUES (1, 'Remise senior 20%', 20, '2026-01-01');
INSERT INTO remise_condition (id_remise, champ, operateur, valeur)
VALUES
(4, 'passager_type', '=', 'senior');


--publicite
INSERT INTO societe (nom) VALUES
('Société Alpha'),
('Société Beta'),
('Société Gamma');

INSERT INTO publicite (id_societe, code, description, duree) VALUES
(1, 'PUB-ALPHA-01', 'Publicité pour le nouveau produit Alpha', 30),
(1, 'PUB-ALPHA-02', 'Promotion spéciale', 45),
(2, 'PUB-BETA-01', 'Publicité pour les services Beta', 60),
(3, 'PUB-GAMMA-01', 'Campagne de lancement Gamma', 25);


INSERT INTO diffusion_publicite_voyage (id_publicite, id_voyage, date_diffusion) VALUES
(1, 1, '2026-01-20 08:00:00'),
(1, 1, '2026-01-21 14:30:00'),
(2, 1, '2026-01-22 09:15:00'),
(3, 1, '2026-01-23 11:00:00'),
(4, 1, '2026-01-24 16:45:00');


INSERT INTO tarif_publicite (montant, date_modif) VALUES
(1000.00, '2026-01-01'),
(1200.50, '2026-01-10'),
(1500.75, '2026-01-15'),
(2000.00, '2026-01-20');
