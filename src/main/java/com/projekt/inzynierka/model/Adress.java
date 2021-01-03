package com.projekt.inzynierka.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "adress")
@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;



    public Adress() {
    }

    public Adress(final Long id, final String town,final String postalCode,final String street,final String number) {
        this.id = id;
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
    }
}
