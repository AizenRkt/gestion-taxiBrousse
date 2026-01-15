package com.example.gestion.service.place;

import com.example.gestion.model.place.Place;
import com.example.gestion.repository.place.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }

    public Place save(Place place) {
        return placeRepository.save(place);
    }

    public void delete(Long id) {
        placeRepository.deleteById(id);
    }
}
