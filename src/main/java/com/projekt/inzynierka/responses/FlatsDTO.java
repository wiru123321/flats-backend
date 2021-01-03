package com.projekt.inzynierka.responses;

import com.projekt.inzynierka.model.Flats;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FlatsDTO {
    private Long id;
    private Integer area;
    private Integer flor;
    private Integer peopleInFlat;
    private Integer rooms;
    private Boolean isBalcony;
    private Boolean isActive;
    private AdressDTO adressDTO;
    private UserAccountDTO userAccountDTO;

    public FlatsDTO() {

    }

    public FlatsDTO(final Flats flats) {
        this.id = flats.getId();
        this.area = flats.getArea();
        this.flor = flats.getFlor();
        this.peopleInFlat = flats.getPeopleInFlat();
        this.rooms = flats.getRooms();
        this.isBalcony = flats.getIsBalcony();
        this.isActive = flats.getIsActive();
        this.adressDTO = new AdressDTO(flats.getAdress());
        this.userAccountDTO = new UserAccountDTO(flats.getUserAccount());
    }

    public FlatsDTO(final Flats flats, final AdressDTO adressDTO, final UserAccountDTO userAccountDTO) {
        this.id = flats.getId();
        this.area = flats.getArea();
        this.flor = flats.getFlor();
        this.peopleInFlat = flats.getPeopleInFlat();
        this.rooms = flats.getRooms();
        this.isBalcony = flats.getIsBalcony();
        this.isActive = flats.getIsActive();
        this.adressDTO = adressDTO;
        this.userAccountDTO = userAccountDTO;
    }

    public FlatsDTO(final Long id,final Integer area,final Integer flor,final Integer peopleInFlat,final Integer rooms,final Boolean isBalcony,final Boolean isActive,final AdressDTO adressDTO,final UserAccountDTO userAccountDTO) {
        this.id = id;
        this.area = area;
        this.flor = flor;
        this.peopleInFlat = peopleInFlat;
        this.rooms = rooms;
        this.isBalcony = isBalcony;
        this.isActive = isActive;
        this.adressDTO = adressDTO;
        this.userAccountDTO = userAccountDTO;
    }
}
