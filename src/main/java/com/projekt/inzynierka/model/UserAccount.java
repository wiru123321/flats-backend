package com.projekt.inzynierka.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "userAccount")
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rentCost;

    @Column(nullable = false)
    private Integer userRentPayment;

    @Column(nullable = false)
    private Integer rubbishCost;

    @Column(nullable = false)
    private Integer userRubbishPayment;

    @Column(nullable = false)
    private Integer waterCost;

    @Column(nullable = false)
    private Integer userWaterCost;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private LocalDateTime userPaymentDate;

    @Column(nullable = false)
    private Boolean isActive;

    public UserAccount() {
    }

    public UserAccount(final Long id, final Integer rentCost,final Integer userRentPayment,final Integer rubbishCost,final Integer userRubbishPayment,final Integer waterCost,final Integer userWaterCost,final LocalDateTime paymentDate,final LocalDateTime userPaymentDate,final Boolean isActive) {
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
