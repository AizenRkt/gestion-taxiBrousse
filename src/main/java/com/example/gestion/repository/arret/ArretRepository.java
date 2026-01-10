package com.example.gestion.repository.arret;

import com.example.gestion.model.Arret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArretRepository extends JpaRepository<Arret, Long> {

}
