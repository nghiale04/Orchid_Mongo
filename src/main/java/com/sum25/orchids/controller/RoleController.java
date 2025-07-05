package com.sum25.orchids.controller;

import com.sum25.orchids.entity.Role;
import com.sum25.orchids.services.RoleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable String id){
      return ResponseEntity.ok(roleService.getRole(id));
    }

}
