package com.example.gestion.service.produit;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.gestion.model.produit.VenteProduit;
import com.example.gestion.repository.produit.VenteProduitRepository;
import com.example.gestion.repository.produit.ProduitPrixRepository;
import com.example.gestion.repository.voyage.VoyageRepository;

@Service
public class ProduitService {

    private final VenteProduitRepository venteProduitRepository;
    private final ProduitPrixRepository produitPrixRepository;
    private final VoyageRepository voyageRepository;

    public ProduitService(VenteProduitRepository venteProduitRepository,
                          ProduitPrixRepository produitPrixRepository,
                          VoyageRepository voyageRepository) {
        this.venteProduitRepository = venteProduitRepository;
        this.produitPrixRepository = produitPrixRepository;
        this.voyageRepository = voyageRepository;
    }

    public BigDecimal getCaProduitsByVoyage(Long idVoyage) {

        var voyage = voyageRepository.findById(idVoyage)
            .orElseThrow(() -> new RuntimeException("Voyage introuvable"));

        LocalDate dateVoyage = voyage.getDateDepart().toLocalDate();

        List<VenteProduit> ventes = venteProduitRepository.findByVoyage(idVoyage);

        BigDecimal total = BigDecimal.ZERO;

        for (VenteProduit vp : ventes) {

            BigDecimal prix = produitPrixRepository
                .findPrixAtDate(vp.getProduit().getIdProduit(), dateVoyage)
                .orElseThrow(() -> new RuntimeException(
                    "Aucun prix trouvé pour le produit : " + vp.getProduit().getNom()
                ))
                .getPrix();

            BigDecimal montant = prix.multiply(
                BigDecimal.valueOf(vp.getNbrProduitVendu())
            );

            total = total.add(montant);
        }

        return total;
    }
}

