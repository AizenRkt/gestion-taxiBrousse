-- donne les trajets
CREATE OR REPLACE VIEW vue_trajets AS
SELECT 
    t.id_trajet,
    t.code_trajet,
    t.description,
    STRING_AGG(a.nom, ' → ' ORDER BY ta.ordre) AS itineraire
FROM trajet t
INNER JOIN trajet_arret ta 
    ON t.id_trajet = ta.id_trajet
INNER JOIN arret a 
    ON ta.id_arret = a.id_arret
GROUP BY 
    t.id_trajet,
    t.code_trajet,
    t.description;

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

