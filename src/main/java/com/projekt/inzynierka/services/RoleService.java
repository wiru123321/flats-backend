package com.projekt.inzynierka.services;


import com.projekt.inzynierka.model.Role;
import com.projekt.inzynierka.repositories.RoleRepository;
import com.projekt.inzynierka.responses.RoleDTO;
import com.projekt.inzynierka.services.interfaces.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role mapRestModel(final Long id, final RoleDTO model) {
        return new Role(id, model.getName());
    }

    @Override
    public Role getEntityByRoleName(final String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public RoleDTO getDTOByRoleName(final String name) {
        final Role role = roleRepository.findByName(name);
        return new RoleDTO(role);
    }

    @Override
    public List<RoleDTO> getAllDTOs() {
        final ArrayList<Role> roleList = new ArrayList<>();
        roleRepository.findAll().forEach(roleList::add);

        return roleList.stream().map(RoleDTO::new).collect(Collectors.toList());
    }
}
