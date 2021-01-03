package com.projekt.inzynierka.responses;

import com.projekt.inzynierka.model.Adress;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdressDTO extends Adress {
    private Long id;
    private String town;
    private String postalCode;
    private String street;
    private String number;

    public AdressDTO() {
    }

    public AdressDTO(final Adress adress) {
        this.id = adress.getId();
        this.town = adress.getTown();
        this.postalCode = adress.getPostalCode();
        this.street = adress.getStreet();
        this.number = adress.getNumber();
    }

    public AdressDTO(final Long id, final String town, final String postalCode, final String street, final String number) {
        this.id = id;
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
    }
}
