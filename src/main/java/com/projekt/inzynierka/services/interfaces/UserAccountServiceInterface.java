package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.UserAccount;
import com.projekt.inzynierka.responses.UserAccountDTO;

public interface UserAccountServiceInterface {
    Long addEntityToDB(UserAccount userAccount);

    UserAccount mapRestModel(Long id, UserAccountDTO userAccount);

    void deleteUserAcc(UserAccount userAccount);

    UserAccount getEntityById(Long id);

}
