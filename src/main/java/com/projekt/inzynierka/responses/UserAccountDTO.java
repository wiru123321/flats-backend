package com.projekt.inzynierka.responses;

import com.projekt.inzynierka.model.UserAccount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class UserAccountDTO extends UserAccount {
    private Long id;
    private Integer rentCost;
    private Integer userRentPayment;
    private Integer rubbishCost;
    private Integer userRubbishPayment;
    private Integer waterCost;
    private Integer userWaterCost;
    private LocalDateTime paymentDate;
    private LocalDateTime userPaymentDate;
    private Boolean isActive;


    public UserAccountDTO() {
    }

    public UserAccountDTO(final UserAccount userAccount) {
        this.id = userAccount.getId();
        this.rentCost = userAccount.getRentCost();
        this.userRentPayment = userAccount.getUserRentPayment();
        this.rubbishCost = userAccount.getRubbishCost();
        this.userRubbishPayment = userAccount.getUserRubbishPayment();
        this.waterCost = userAccount.getWaterCost();
        this.userWaterCost = userAccount.getUserWaterCost();
        this.paymentDate = userAccount.getPaymentDate();
        this.userPaymentDate = userAccount.getUserPaymentDate();
        this.isActive = userAccount.getIsActive();
    }

    public UserAccountDTO(final Long id,final Integer rentCost,final Integer userRentPayment,final Integer rubbishCost,final Integer userRubbishPayment,final Integer waterCost,final Integer userWaterCost,final LocalDateTime paymentDate,final LocalDateTime userPaymentDate,final Boolean isActive) {
        this.id = id;
        this.rentCost = rentCost;
        this.userRentPayment = userRentPayment;
        this.rubbishCost = rubbishCost;
        this.userRubbishPayment = userRubbishPayment;
        this.waterCost = waterCost;
        this.userWaterCost = userWaterCost;
        this.paymentDate = paymentDate;
        this.userPaymentDate = userPaymentDate;
        this.isActive = isActive;
    }
}
