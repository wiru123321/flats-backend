package com.projekt.inzynierka.services.interfaces;

import com.projekt.inzynierka.model.Role;
import com.projekt.inzynierka.responses.RoleDTO;

import java.util.List;

public interface RoleServiceInterface {
    Role mapRestModel(Long id, RoleDTO model);

    Role getEntityByRoleName(String roleName);

    RoleDTO getDTOByRoleName(String name);

    List<RoleDTO> getAllDTOs();
}

