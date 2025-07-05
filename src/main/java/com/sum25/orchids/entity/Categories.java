package com.sum25.orchids.entity;

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
@Document(collection = "categories")
public class Categories {

    @Id
    private String id;

    private String categoryName;

    @DBRef
    private List<Orchids> orchids;

    public Categories(long l, String natural) {
    }
}
