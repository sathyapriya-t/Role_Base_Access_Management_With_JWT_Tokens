package com.spring.security.service;

import com.spring.security.domain.Product;
import com.spring.security.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Iterable<Product> addAllProduct() {
        List<Product> products = new ArrayList<>();
        IntStream.range(1, 11).forEach(value -> {
            Product product = new Product();
            product.setId(value);
            product.setName("pen" + value);
            product.setPrize(20 + value);
            products.add(product);
        });
        System.out.println(products);
        return productRepo.saveAll(products);
    }

    public Product getProduct(int id) {
        return productRepo.findById(id).orElse(null);
    }
}
