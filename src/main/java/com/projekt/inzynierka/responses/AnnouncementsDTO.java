package com.projekt.inzynierka.responses;

import com.projekt.inzynierka.model.Announcements;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class AnnouncementsDTO {
    private Long id;
    private String adminMessage;
    private Integer index;
    private Boolean isActive;
    private String title;
    private LocalDateTime data;

    public AnnouncementsDTO(final Announcements announcements) {
        this.id = announcements.getId();
        this.adminMessage = announcements.getAdminMessage();
        this.isActive = announcements.getIsActive();
        this.title = announcements.getTitle();
        this.data = announcements.getData();
        this.index = announcements.getIndex();
    }
}
