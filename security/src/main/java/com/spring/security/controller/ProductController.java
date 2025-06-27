package com.spring.security.controller;

import com.spring.security.domain.Product;
import com.spring.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping("/welcome")
    public String print(){
        return "Hello, Yey!!";
    }

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('READ_PRIVILEGE') and hasAuthority('EXECUTE_PRIVILEGE')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.addProduct(product));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Iterable<Product> addAllProduct(){
        return service.addAllProduct();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // OR @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Product getProduct(@PathVariable int id){
        return service.getProduct(id);
    }
}
