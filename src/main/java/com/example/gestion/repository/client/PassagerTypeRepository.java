package com.example.gestion.repository.client;

import com.example.gestion.model.client.PassagerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagerTypeRepository extends JpaRepository<PassagerType, Long> {
}
