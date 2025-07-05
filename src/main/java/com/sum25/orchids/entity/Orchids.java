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
@Document(collection = "orchids")
public class Orchids {

    @Id
    private String id;

    private Boolean isNatural;

    private String orchidDescription;

    private String orchidName;

    private String orchidUrl;

    private Integer price;

    @DBRef
    @JsonIgnore
    private List<OrderDetails> orderDetails;

    private Categories category;

    public Orchids(String o1, boolean b, String beautifulNaturalOrchid, String orchidA, String url, double v, String c1) {
    }
}
