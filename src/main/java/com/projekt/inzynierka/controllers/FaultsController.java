package com.projekt.inzynierka.controllers;

import com.projekt.inzynierka.model.Faults;
import com.projekt.inzynierka.model.Flats;
import com.projekt.inzynierka.repositories.FaultsRepository;
import com.projekt.inzynierka.responses.FaultsDTO;
import com.projekt.inzynierka.services.FaultsService;
import com.projekt.inzynierka.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FaultsController {

    private final FaultsService faultsService;
    private final UserService userService;
    private final FaultsRepository faultsRepository;

    @Autowired
    public FaultsController(FaultsService faultsService, UserService userService, FaultsRepository faultsRepository) {
        this.faultsService = faultsService;
        this.userService = userService;
        this.faultsRepository = faultsRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/u/addFault/{login}")
    public ResponseEntity<?> addFaultToDatabase(@PathVariable final String login,@RequestBody final Faults userFault) {
        final Flats flats = userService.getEntityByLogin(login).getFlats();
        final Faults newFault = faultsService.mapCreationModel(null,userFault,flats.getId());
        return ResponseEntity.ok(faultsService.addEntityToDB(newFault));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/r/getActiveFaults/{login}")
    public ResponseEntity<?> getActiveFaults(@PathVariable final String login) {
        final Long flat_id = userService.getEntityByLogin(login).getFlats().getId();
        final List<FaultsDTO> faultsDTOList = faultsService.getActiveFaultsDTOs(flat_id);
        return ResponseEntity.ok(faultsDTOList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/r/getActiveFaults")
    public ResponseEntity getAllActiveFaults() {
        return ResponseEntity.ok(faultsService.getAllActiveFaultsDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/r/getDisActiveFaults")
    public ResponseEntity getAllDisActiveFaults() {
        return ResponseEntity.ok(faultsService.getAllDisActiveFaultsDTOs());
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/a/disactivateUserFault/{id}")
    public ResponseEntity<?> updateDataBaseFaults(@PathVariable final Long id,@RequestBody final Faults userFault) throws Exception {
        if (!faultsService.checkIfFaultWithIdExists(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no fault with passed id.");
        }
        return ResponseEntity.ok(faultsService.updateUserFaultInDB(id,userFault));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/u/deleteUserFault/{id}")
    public ResponseEntity<?> setUserFaultAsDeletedInDB(@PathVariable final Long id) throws Exception {
        if (!faultsService.checkIfFaultWithIdExists(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no fault with passed id.");
        }
        faultsService.deleteUserFault(id);
        return ResponseEntity.ok("Object was deleted");
    }
}
