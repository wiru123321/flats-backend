package com.projekt.inzynierka.repositories;

import com.projekt.inzynierka.model.Announcements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcements, Long> {

    List<Announcements> findAllByIsActive(Boolean isActive);

    Announcements findByIndex(Integer index);

    Boolean existsByIndex(Integer index);
}
