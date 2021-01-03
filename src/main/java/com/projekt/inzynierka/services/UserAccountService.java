package com.projekt.inzynierka.services;

import com.projekt.inzynierka.model.UserAccount;
import com.projekt.inzynierka.repositories.UserAccountRepository;
import com.projekt.inzynierka.responses.UserAccountDTO;
import com.projekt.inzynierka.services.interfaces.UserAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserAccountServiceInterface {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(final UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Long addEntityToDB(final UserAccount userAccount) {
        return userAccountRepository.save(userAccount).getId();
    }

    @Override
    public UserAccount mapRestModel(final Long id, final UserAccountDTO userAccount) {
        return new UserAccount(id, userAccount.getRentCost(), userAccount.getUserRentPayment(), userAccount.getRentCost(), userAccount.getUserRubbishPayment(),userAccount.getWaterCost(),userAccount.getUserWaterCost(),userAccount.getPaymentDate(),userAccount.getUserPaymentDate(),userAccount.getIsActive());
    }
    @Override
    public void deleteUserAcc(final UserAccount userAccount) {
        userAccountRepository.delete(userAccount);
    }
    @Override
    public UserAccount getEntityById(final Long id) {
        return userAccountRepository.findById(id).get();
    }


}
