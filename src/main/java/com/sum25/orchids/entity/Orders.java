package com.sum25.orchids.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class Orders {
    @Id
    private String id;

    private LocalDate orderDate;

    private String status;

    private Integer totalAmount;

    @DBRef
    private Accounts account;


    public Orders(String o100, String a1, LocalDate now, String pending, double v) {
    }
}
