package com.sum25.orchids.repository;

import com.sum25.orchids.entity.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryrRepository  extends MongoRepository<Categories, String> {
    Categories findByCategoryName(String categoryName);
}
