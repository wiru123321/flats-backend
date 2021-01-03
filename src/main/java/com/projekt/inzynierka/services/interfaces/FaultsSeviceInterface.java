package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.Faults;

public interface FaultsSeviceInterface {
    Long addEntityToDB(Faults faults);

    Faults mapCreationModel(Long id, Faults faults, Long flatsId);

    Boolean checkIfFaultWithIdExists(Long id);

    Long updateUserFaultInDB(Long id, Faults userFault) throws Exception;

    void deleteUserFault(Long id) throws Exception;

}
