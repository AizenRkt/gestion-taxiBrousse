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

INSERT INTO place (code) VALUES
('P01'), ('P02'), ('P03'), ('P04'), ('P05'),
('P06'), ('P07'), ('P08'), ('P09'), ('P10'),
('P11'), ('P12'), ('P13'), ('P14'), ('P15'),
('P16'), ('P17'), ('P18'), ('P19'), ('P20');

INSERT INTO place_type (libelle) VALUES ('économique'), ('standard'), ('VIP');

INSERT INTO tarif_place_type (id_place_type, montant, date_tarif) VALUES
(1, 80000, '2026-01-01'),  -- eco
(2, 140000, '2026-01-01'),  -- standart
(3, 180000, '2026-01-01');  -- vip

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

-- -- 6 places Premium
-- DO $$
-- DECLARE i INT;
-- BEGIN
--   FOR i IN 1..6 LOOP
--     INSERT INTO place (code) VALUES ('P' || i);
--     INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
--     VALUES (5, currval('place_id_place_seq'), 2);
--   END LOOP;
-- END $$;

-- -- 10 places Standard
-- DO $$
-- DECLARE i INT;
-- BEGIN
--   FOR i IN 1..10 LOOP
--     INSERT INTO place (code) VALUES ('S' || i);
--     INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
--     VALUES (5, currval('place_id_place_seq'), 1);
--   END LOOP;
-- END $$;

-- -- 2 places VIP
-- DO $$
-- DECLARE i INT;
-- BEGIN
--   FOR i IN 1..2 LOOP
--     INSERT INTO place (code) VALUES ('V' || i);
--     INSERT INTO vehicule_place (id_vehicule, id_place, id_place_type)
--     VALUES (5, currval('place_id_place_seq'), 3);
--   END LOOP;
-- END $$;

-- UPDATE tarif_place_type
-- SET montant = 90000.00,
--     date_tarif = '2026-01-20'
-- WHERE id_tarif_place_type = 1;