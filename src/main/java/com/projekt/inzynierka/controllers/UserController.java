package com.projekt.inzynierka.controllers;

import com.projekt.inzynierka.model.Adress;
import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.model.User;
import com.projekt.inzynierka.model.UserAccount;
import com.projekt.inzynierka.responses.User.UserCreation;
import com.projekt.inzynierka.responses.User.UserDTO;
import com.projekt.inzynierka.services.AdressService;
import com.projekt.inzynierka.services.FlatsService;
import com.projekt.inzynierka.services.UserAccountService;
import com.projekt.inzynierka.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final AdressService adressService;
    private final FlatsService flatsService;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    public UserController(final UserService userService, AdressService adressService, FlatsService flatsService, UserAccountService userAccountService, final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.adressService = adressService;
        this.flatsService = flatsService;
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/a/users")
    public ResponseEntity<?> getAllActiveUsersExceptCurrent() {
        final List<UserDTO> userDTOList = userService.getAllActiveUserDTOs();
        userDTOList.remove(userService.getDTOByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.ok(userDTOList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/u/getUsers/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable final String login) {
        return ResponseEntity.ok(userService.getDTOByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/a/user")
    public ResponseEntity<?> addUserToDatabase(@RequestBody final UserCreation userCreation) {
        if (userService.checkIfUserWithLoginExists(userCreation.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Existing login given.");
        }
        final Adress adress = adressService.mapRestModel(null,userCreation.getFlatsDTO().getAdressDTO());
        final Long adressId = adressService.addEntityToDB(adress);
        final UserAccount userAccount = userAccountService.mapRestModel(null,userCreation.getFlatsDTO().getUserAccountDTO());
        final Long userAccountId = userAccountService.addEntityToDB(userAccount);
        final Flats flats = flatsService.mapRestModel(null,userCreation.getFlatsDTO(),adressId,userAccountId);
        final Long flatId = flatsService.addEntityToDB(flats);
        final User user = userService.mapCreationModel(null, userCreation,flatId);
        user.setPassword(passwordEncoder.encode(userCreation.getPassword()));
        return ResponseEntity.ok(userService.addEntityToDB(user));
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/a/user/{login}")
    public ResponseEntity<?> updateDataBaseUser(@PathVariable final String login, @RequestBody final UserAccount userAccount) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        return ResponseEntity.ok(userService.updateUserInDB(login, userAccount));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/u/userInfo/{login}")
    public ResponseEntity<?> updateDataBaseUserInfo(@PathVariable final String login, @RequestBody final User user) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        return ResponseEntity.ok(userService.updateUserInfoInDB(login, user));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/user/{login}")
    public ResponseEntity<?> setUserAsDeletedInDB(@PathVariable final String login) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        return ResponseEntity.ok(userService.setUserIsNotActive(login));
    }
}
