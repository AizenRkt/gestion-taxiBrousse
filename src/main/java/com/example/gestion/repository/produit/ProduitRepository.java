package com.example.gestion.repository.produit;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.model.produit.Produit;   
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
