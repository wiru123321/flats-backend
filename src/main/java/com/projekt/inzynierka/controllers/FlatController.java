package com.projekt.inzynierka.controllers;

import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FlatController {

    private final UserService userService;

    public FlatController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/u/userFlat/{login}")
    public ResponseEntity<?> updateDataBaseUserInfo(@PathVariable final String login, @RequestBody final Flats flats) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        return ResponseEntity.ok(userService.updateUserFlatsInfoInDB(login, flats));
    }
}
