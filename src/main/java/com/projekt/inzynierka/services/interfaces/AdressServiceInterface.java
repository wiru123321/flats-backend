package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.Adress;
import com.projekt.inzynierka.responses.AdressDTO;

public interface AdressServiceInterface {
    Long addEntityToDB(Adress adress);

    Adress mapRestModel(Long id, AdressDTO adress);

    void deleteAdress(Adress adress);

    Adress getEntityById(Long id);
}
