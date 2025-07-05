package com.sum25.orchids.repository;

import com.sum25.orchids.entity.OrderDetails;
import com.sum25.orchids.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository  extends MongoRepository<OrderDetails,String> {
    List<OrderDetails> findByOrder(Orders order);

}
