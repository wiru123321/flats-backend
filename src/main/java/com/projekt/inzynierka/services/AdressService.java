package com.projekt.inzynierka.services;

import com.projekt.inzynierka.model.Adress;
import com.projekt.inzynierka.repositories.AdressRepository;
import com.projekt.inzynierka.responses.AdressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressService implements com.projekt.inzynierka.services.interfaces.AdressServiceInterface {
    private final AdressRepository adressRepository;

    @Autowired
    public AdressService(final AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    @Override
    public Long addEntityToDB(final Adress adress) {
        return adressRepository.save(adress).getId();
    }

    @Override
    public Adress mapRestModel(final Long id, final AdressDTO adress) {
        return new Adress(id, adress.getTown(), adress.getPostalCode(), adress.getStreet(), adress.getNumber());
    }
    @Override
    public void deleteAdress(final Adress adress) {
        adressRepository.delete(adress);
    }
    @Override
    public Adress getEntityById(final Long id) {
        return adressRepository.findById(id).get();
    }
}
