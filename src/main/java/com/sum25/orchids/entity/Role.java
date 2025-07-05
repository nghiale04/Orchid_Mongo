package com.sum25.orchids.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "role")
public class Role {
    @Id
    private String id;

    private String roleName;

    @DBRef
    @JsonIgnore
    private List<Accounts> accountsList;

}
