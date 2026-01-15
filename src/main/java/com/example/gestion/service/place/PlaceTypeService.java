package com.example.gestion.service.place;

import com.example.gestion.model.place.PlaceType;
import com.example.gestion.repository.place.PlaceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceTypeService {

    private final PlaceTypeRepository placeTypeRepository;

    public PlaceTypeService(PlaceTypeRepository placeTypeRepository) {
        this.placeTypeRepository = placeTypeRepository;
    }

    public List<PlaceType> findAll() {
        return placeTypeRepository.findAll();
    }

    public Optional<PlaceType> findById(Long id) {
        return placeTypeRepository.findById(id);
    }

    public PlaceType save(PlaceType pt) {
        return placeTypeRepository.save(pt);
    }

    public void delete(Long id) {
        placeTypeRepository.deleteById(id);
    }
}
