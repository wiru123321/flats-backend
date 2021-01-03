package com.projekt.inzynierka.repositories;

import com.projekt.inzynierka.model.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends CrudRepository<Adress, Long> {
}
