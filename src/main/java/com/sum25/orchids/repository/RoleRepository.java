package com.sum25.orchids.repository;

import com.sum25.orchids.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleById(String id);

}
