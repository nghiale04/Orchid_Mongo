package com.sum25.orchids.services.Impl;

import com.sum25.orchids.entity.Role;
import com.sum25.orchids.repository.RoleRepository;
import com.sum25.orchids.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private  RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }


}
