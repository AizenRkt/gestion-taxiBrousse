package com.example.gestion.repository.remise;

import com.example.gestion.model.remise.Remise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemiseRepository extends JpaRepository<Remise, Long> {
}
