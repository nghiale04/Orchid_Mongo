package com.sum25.orchids.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "order_details")
public class OrderDetails {

    @Id
    private String id;

    private Integer price;
    private Integer quantity;

    @DBRef
    private Orders order;

    @DBRef
    private Orchids orchid;

}
