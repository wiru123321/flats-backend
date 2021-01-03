package com.projekt.inzynierka.responses;

import com.projekt.inzynierka.model.Faults;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FaultsDTO {
    private Long id;
    private FlatsDTO flatsDTO;
    private String describe;
    private String title;
    private Boolean isActive;

    public FaultsDTO(final Faults faults, final FlatsDTO flatsDTO) {
        this.id = faults.getId();
        this.describe = faults.getDescribe();
        this.isActive = faults.getIsActive();
        this.title = faults.getTitle();
        this.flatsDTO = flatsDTO;
    }

    public FaultsDTO(final Long id,final FlatsDTO flatsDTO,final String describe,final String title,final Boolean isActive) {
        this.id = id;
        this.flatsDTO = flatsDTO;
        this.describe = describe;
        this.title = title;
        this.isActive = isActive;
    }
}
