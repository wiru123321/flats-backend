package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.responses.FlatsDTO;

public interface FlatsServiceInterface {
    Long addEntityToDB(Flats flats);

    Flats mapRestModel(Long id, FlatsDTO flats,Long adressId,Long userAccountId);

    void deleteFlat(Flats flats);

    Flats getEntityById(Long id);

}
