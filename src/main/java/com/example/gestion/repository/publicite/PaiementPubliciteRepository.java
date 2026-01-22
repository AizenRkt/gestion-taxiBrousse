package com.example.gestion.repository.publicite;

import com.example.gestion.model.publicite.PaiementPublicite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementPubliciteRepository
        extends JpaRepository<PaiementPublicite, Integer> {
}
