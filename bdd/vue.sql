CREATE VIEW vue_trajets AS
SELECT 
    t.id_trajet,
    t.code_trajet,
    t.description,
    STRING_AGG(a.nom, ' → ' ORDER BY ta.ordre) AS itineraire
FROM trajet t
JOIN trajet_arret ta ON t.id_trajet = ta.id_trajet
JOIN arret a ON ta.id_arret = a.id_arret
GROUP BY t.id_trajet, t.code_trajet, t.description
ORDER BY t.id_trajet;
