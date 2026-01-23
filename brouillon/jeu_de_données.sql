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
curl -X POST http://localhost:8080/api/reservations/achat \
  -H "Content-Type: application/json" \
  -d '{
    "idVoyage": 1,
    "clientNom": "Rakoto Jean",
    "clientTel": "0329876543",
    "details": [
      {
        "idPlaceType": 1,
        "idPassagerType": 2,
        "nombrePlaces": 2
      } 
    ]
  }'


curl -X GET "http://localhost:8080/api/publicites/diffusionsByVoyage?date=2026-01-01&idVoyage=1"

curl http://localhost:8080/api/publicites/diffusionsByVoyage

curl -X GET "http://localhost:8080/api/publicites/diffusionsByVoyage?annee=2026&mois=1&idVoyage=1" \
     -H "Accept: application/json"

/api/publicites/caVoyage?idVoyage=1

curl -X GET "http://localhost:8080/api/reservations/caByVoyage?idVoyage=1" \
     -H "Accept: application/json"
