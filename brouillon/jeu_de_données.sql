INSERT INTO passager_type (libelle) VALUES
('adulte'),
('enfant'),
('senior');

INSERT INTO remise_type (libelle) VALUES('POURCENTAGE'), ('MONTANT_FIXE');

INSERT INTO place_type (libelle) VALUES ('économique'), ('premium'), ('VIP');

INSERT INTO tarif_place_type (id_place_type, montant, date_tarif) VALUES
(1, 50000, '2026-01-01'),  -- eco
(2, 60000, '2026-01-01'),  -- premium
(3, 70000, '2026-01-01');  -- vip

-- vip enfant 65000
-- premium enfant 50000
-- eco enfant 40000

-- tarif senior = tarif adulte - 20% 

