package com.projekt.inzynierka.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "announcements")
@Entity
public class Announcements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String adminMessage;

    @Column(nullable = false)
    private Integer index;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime data;

    public Announcements() {
    }

    public Announcements(final Long id, final String adminMessage,final Boolean isActive,final String title,final LocalDateTime data,Integer index) {
        this.id = id;
        this.adminMessage = adminMessage;
        this.isActive = isActive;
        this.title = title;
        this.data = data;
        this.index = index;
    }
}
