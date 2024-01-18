package com.wannacode.productmicroservice.controller;

import com.wannacode.productmicroservice.entity.ProductEntity;
import com.wannacode.productmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();

    }

//  ESTE ES UN EJEMPLO DE COMO SE HARIA LA RESPUESTA HTTP EN CASO DE NO QUERER USAR EL @ResponseStatus
//    @GetMapping
//    public ResponseEntity<List<ProductEntity>> getAllProducts(){
//    List<ProductEntity> productEntities = productRepository.findAll();
//    ResponseEntity<List<ProductEntity>> responseEntity = new ResponseEntity<>(productEntities, HttpStatus.OK);
//
//    return responseEntity;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)// esto sirve para que spring envie un mensaje de OK . facilita mucho la respuesta Http
    public void createProduct(@RequestBody ProductEntity productEntity){
        productRepository.save(productEntity);
    }
}
