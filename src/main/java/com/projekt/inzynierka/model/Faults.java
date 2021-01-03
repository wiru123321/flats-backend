package com.projekt.inzynierka.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "faults")
@Entity
public class Faults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Flats flats;

    @Column(nullable = false)
    private String describe;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isActive;




    public Faults() {
    }

    public Faults(final Long id,final Flats flats,final String describe,final String title,final Boolean isActive) {
        this.id = id;
        this.flats = flats;
        this.describe = describe;
        this.title = title;
        this.isActive = isActive;
    }

    public Faults(final Long id,final Faults faults, final Flats flats) {
        this.id = id;
        this.flats = flats;
        this.describe = faults.getDescribe();
        this.title = faults.getTitle();
        this.isActive = faults.getIsActive();
    }
}
