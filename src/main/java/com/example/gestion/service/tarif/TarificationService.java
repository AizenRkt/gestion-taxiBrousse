package com.example.gestion.service.tarif;

import com.example.gestion.model.client.PassagerType;
import com.example.gestion.model.place.PlaceType;
import com.example.gestion.model.tarif.TarifPlaceType;
import com.example.gestion.model.remise.Remise;
import com.example.gestion.model.remise.RemiseCondition;
import com.example.gestion.repository.tarif.TarifPlaceTypeRepository;
import com.example.gestion.repository.remise.RemiseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TarificationService {

    private final TarifPlaceTypeRepository tarifRepo;
    private final RemiseRepository remiseRepo;

    public TarificationService(TarifPlaceTypeRepository tarifRepo, RemiseRepository remiseRepo) {
        this.tarifRepo = tarifRepo;
        this.remiseRepo = remiseRepo;
    }

    public BigDecimal calculerPrix(PlaceType placeType, PassagerType passagerType) {

        Optional<TarifPlaceType> tarifBaseOpt = tarifRepo.findById(placeType.getIdPlaceType());
        if (tarifBaseOpt.isEmpty()) {
            throw new RuntimeException("Pas de tarif trouvé pour ce type de place");
        }
        BigDecimal tarifBase = tarifBaseOpt.get().getMontant();

        List<Remise> remises = remiseRepo.findAll();

        for (Remise remise : remises) {
            for (RemiseCondition cond : remise.getConditions()) {
                if (cond.getChamp().equals("passager_type") && cond.getValeur().equals(passagerType.getLibelle())
                        || cond.getChamp().equals("place_type") && cond.getValeur().equals(placeType.getLibelle())) {

                    if (remise.getType().getIdTypeRemise() == 2) {
                        tarifBase = remise.getValeur();
                    } else if (remise.getType().getIdTypeRemise() == 1) { 
                        BigDecimal reduction = tarifBase.multiply(remise.getValeur().divide(BigDecimal.valueOf(100)));
                        tarifBase = tarifBase.subtract(reduction);
                    }
                }
            }
        }

        return tarifBase;
    }
}
