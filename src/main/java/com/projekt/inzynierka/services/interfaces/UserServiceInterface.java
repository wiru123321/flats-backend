package com.projekt.inzynierka.services.interfaces;


import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.model.User;
import com.projekt.inzynierka.model.UserAccount;
import com.projekt.inzynierka.responses.User.UserCreation;
import com.projekt.inzynierka.responses.User.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    //Long updateUserInDB(String login, UserUpdate userUpdate);
    Long updateUserInDB(String login);

    Long updateUserInDB(String login, UserAccount userAccount);

    Long updateUserInfoInDB(String login, User user);

    Long addEntityToDB(User user);

    Long setUserIsNotActive(String login);

    Boolean checkIfUserWithLoginExists(String login);

    boolean checkPassword(String given, String actual);

    boolean changePassword(User user, String password);

    boolean checkEmail(String email);

    boolean checkPhoneNumber(String phoneNumber);

    void changeEmail(User user, String email);

    void changePhoneNumber(User user, String phoneNumber);

    //NO PASSWORD MAPPING
    User mapRestModel(Long id, UserDTO userDTO, Long flatId);

    User mapCreationModel(Long id, UserCreation userCreation, Long flatId);

    User getEntityByLogin(String name);

    User getEntityByLoginAndisActive(final String login,final Boolean isActive);

    User getEntityByEmail(String email);

    User mapCreationModel(Long id, UserCreation userCreation);


    UserDTO getDTOByEmail(String email);

    UserDTO getDTOByLogin(String login);

    List<UserDTO> getAllDTOs();

    List<UserDTO> getAllActiveUserDTOs();

    Long updateUserFlatsInfoInDB(String login, Flats flats);
}

