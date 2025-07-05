package com.sum25.orchids.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "accounts")
public class Accounts implements UserDetails {

    @Id
    private String id;

    @Field(value = "account_name")
    private String accountName;

    @Field(value = "email")
    private String email;

    @Field(value = "password")
    private String password;

    @DBRef
    private Role role;

    @JsonIgnore
    @DBRef
    private List<Orders> ordersList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}