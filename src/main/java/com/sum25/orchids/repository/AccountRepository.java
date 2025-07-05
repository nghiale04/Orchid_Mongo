package com.sum25.orchids.repository;

import com.sum25.orchids.entity.Accounts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Accounts, String> {
    Optional<Accounts> findByEmail(String email);
}

