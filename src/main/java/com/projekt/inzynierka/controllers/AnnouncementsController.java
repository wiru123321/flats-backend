package com.projekt.inzynierka.controllers;

import com.projekt.inzynierka.model.Announcements;
import com.projekt.inzynierka.responses.AnnouncementsDTO;
import com.projekt.inzynierka.services.AnnouncementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    public AnnouncementsController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/a/addAnnouncement")
    public ResponseEntity<?> addAnnouncementToDatabase(@RequestBody final Announcements announcements) {
        return ResponseEntity.ok(announcementsService.addEntityToDB(announcements));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/r/announcement")
    public ResponseEntity<?> getAllActiveAnnouncement() {
        final List<AnnouncementsDTO> announcementDTOList = announcementsService.getAllActiveAnnouncementsDTOs();
        return ResponseEntity.ok(announcementDTOList);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/deleteAnnouncement/{index}")
    public ResponseEntity<?> setAnnouncementAsDeletedInDB(@PathVariable final Integer index) {
        if (!announcementsService.checkIfAnnouncementWithIndexExists(index)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no announcement with passed announcementId.");
        }

        return ResponseEntity.ok(announcementsService.setAnnouncementsIsNotActive(index));
    }
}
