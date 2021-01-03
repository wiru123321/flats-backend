package com.projekt.inzynierka.repositories;

import com.projekt.inzynierka.model.Flats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatsRepository extends CrudRepository<Flats, Long> {

}
