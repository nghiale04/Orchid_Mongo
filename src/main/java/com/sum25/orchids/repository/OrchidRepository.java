package com.sum25.orchids.repository;

import com.sum25.orchids.entity.Orchids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrchidRepository extends MongoRepository<Orchids,String> {
}
