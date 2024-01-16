package com.wannacode.productmicroservice.repository;


import com.wannacode.productmicroservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ProductRepository extends MongoRepository<ProductEntity, String> { // esta clase brinda acceso a los metodos saveAll(), find(), findAll(), etc.

}
