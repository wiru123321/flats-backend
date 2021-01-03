package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.Announcements;
import com.projekt.inzynierka.responses.AnnouncementsDTO;

import java.util.List;

public interface AnnouncementsSeviceInterface {
    Long addEntityToDB(Announcements announcements);

    List<AnnouncementsDTO> getAllActiveAnnouncementsDTOs();

    Boolean checkIfAnnouncementWithIndexExists(Integer Index);

    Announcements getEntityByIndex(Integer Index);

    Long setAnnouncementsIsNotActive(Integer Index);
}
