-- donne les trajets
-- CREATE OR REPLACE VIEW vue_trajets AS
-- SELECT 
--     t.id_trajet,
--     t.code_trajet,
--     t.description,
--     STRING_AGG(a.nom, ' → ' ORDER BY ta.ordre) AS itineraire
-- FROM trajet t
-- INNER JOIN trajet_arret ta 
--     ON t.id_trajet = ta.id_trajet
-- INNER JOIN arret a 
--     ON ta.id_arret = a.id_arret
-- GROUP BY 
--     t.id_trajet,
--     t.code_trajet,
--     t.description;

-- donne le chiffre d'affaire par trajet
CREATE OR REPLACE VIEW chiffre_affaire_trajet AS
SELECT 
    t.id_trajet,
    t.code_trajet,
    t.description,
    SUM(r.total_payer) AS chiffre_affaire_total
FROM trajet t
JOIN voyage v ON v.id_trajet = t.id_trajet
JOIN reservation r ON r.id_voyage = v.id_voyage
GROUP BY t.id_trajet, t.code_trajet, t.description
ORDER BY t.id_trajet;

CREATE OR REPLACE VIEW vehicule_place_summary AS
WITH place_count AS (
    SELECT
        vp.id_vehicule,
        vp.id_place_type,
        COUNT(*) AS nombre_places
    FROM vehicule_place vp
    GROUP BY vp.id_vehicule, vp.id_place_type
)
SELECT
    v.id_vehicule,
    v.immatriculation,
    v.marque,
    pt.libelle AS place_type,
    pc.nombre_places,
    pc.nombre_places * MAX(tpt.montant) AS montant_max
FROM place_count pc
JOIN vehicule v ON v.id_vehicule = pc.id_vehicule
JOIN place_type pt ON pt.id_place_type = pc.id_place_type
JOIN tarif_place_type tpt ON tpt.id_place_type = pc.id_place_type
GROUP BY v.id_vehicule, v.immatriculation, v.marque, pt.libelle, pc.nombre_places
ORDER BY v.id_vehicule, pt.libelle;
