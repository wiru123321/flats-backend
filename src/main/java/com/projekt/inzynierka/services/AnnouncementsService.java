package com.projekt.inzynierka.services;

import com.projekt.inzynierka.model.Announcements;
import com.projekt.inzynierka.repositories.AnnouncementsRepository;
import com.projekt.inzynierka.responses.AnnouncementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementsService implements com.projekt.inzynierka.services.interfaces.AnnouncementsSeviceInterface {
    private final AnnouncementsRepository announcementsRepository;

    @Autowired
    public AnnouncementsService(final AnnouncementsRepository announcementsRepository) {
        this.announcementsRepository = announcementsRepository;
    }

    @Override
    public Long addEntityToDB(final Announcements announcements) {
        return announcementsRepository.save(announcements).getId();
    }

    @Override
    public List<AnnouncementsDTO> getAllActiveAnnouncementsDTOs() {
        final ArrayList<Announcements> announcementsArrayList = new ArrayList<>(announcementsRepository.findAllByIsActive(true));
        return this.mapRestList(announcementsArrayList);
    }


    private List<AnnouncementsDTO> mapRestList(final List<Announcements> announcementsArrayList) {
        final ArrayList<AnnouncementsDTO> announcementsDTOArrayList = new ArrayList<>();
        announcementsArrayList.forEach((announcements) -> {
            final AnnouncementsDTO announcementsDTO = new AnnouncementsDTO(announcements);
            announcementsDTOArrayList.add(announcementsDTO);
        });
        return announcementsDTOArrayList;
    }

    @Override
    public Boolean checkIfAnnouncementWithIndexExists(final Integer index) {
        return announcementsRepository.existsByIndex(index);
    }

    @Override
    public Announcements getEntityByIndex(final Integer index) {
        return announcementsRepository.findByIndex(index);
    }

    @Override
    public Long setAnnouncementsIsNotActive(final Integer index) {
        final Announcements announcements = this.getEntityByIndex(index);
        announcements.setIsActive(false);
        return announcementsRepository.save(announcements).getId();
    }
}
