package com.example.gestion.controller.produit.api;
import com.example.gestion.service.produit.ProduitService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin
public class ProduitApiController {

    private final ProduitService produitService;

    public ProduitApiController(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * 💰 Retourne le CA généré par les produits pour un voyage
     *
     * Exemple:
     * GET /api/produits/caByVoyage?idVoyage=3
     */
    @GetMapping("/caByVoyage")
    public BigDecimal getCaProduitsByVoyage(@RequestParam Long idVoyage) {
        return produitService.getCaProduitsByVoyage(idVoyage);
    }
}
