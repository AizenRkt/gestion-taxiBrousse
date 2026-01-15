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
INSERT INTO trajet_arret (id_trajet, id_arret, ordre) VALUES (1, 2, 3); -- Tamatave

INSERT INTO trajet_tarif (id_trajet, montant, date_tarif)
VALUES (1, 30000, '2024-01-01');

-- donées de voyage 
INSERT INTO voyage (id_trajet, id_vehicule, id_chauffeur, date_depart, date_arrivee)
VALUES (1, 1, 1, '2024-07-01 08:00:00', '2024-07-01 18:00:00');

INSERT INTO tarif_place_type (id_place_type, montant, date_tarif) VALUES
(1, 80000, '2026-01-01'),  -- Standard
(2, 140000, '2026-01-01');  -- Premium