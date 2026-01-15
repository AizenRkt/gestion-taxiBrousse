package com.example.gestion.service.tarif;

import com.example.gestion.model.tarif.TarifPlaceType;
import com.example.gestion.repository.tarif.TarifPlaceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifPlaceTypeService {

    private final TarifPlaceTypeRepository repo;

    public TarifPlaceTypeService(TarifPlaceTypeRepository repo) {
        this.repo = repo;
    }

    public List<TarifPlaceType> findAll() {
        return repo.findAll();
    }

    public Optional<TarifPlaceType> findById(Long id) {
        return repo.findById(id);
    }

    public TarifPlaceType save(TarifPlaceType tpt) {
        return repo.save(tpt);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
