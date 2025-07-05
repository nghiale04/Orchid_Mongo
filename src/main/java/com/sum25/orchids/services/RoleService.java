package com.sum25.orchids.services;


import com.sum25.orchids.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRole(String id);
    List<Role> getAllRole();
}
