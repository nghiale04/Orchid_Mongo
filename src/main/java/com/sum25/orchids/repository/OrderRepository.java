package com.sum25.orchids.repository;

import com.sum25.orchids.entity.Accounts;
import com.sum25.orchids.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Orders,String> {
    List<Orders> findAllByAccount(Accounts account);
}
