package com.apbg.webstore.productservice.web;

import com.apbg.webstore.productservice.domain.Product;
import com.apbg.webstore.productservice.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all-products")
    public ResponseEntity getAll() {
        return new ResponseEntity(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity getPage(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity(productRepository.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getById(@PathVariable int id) {

        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent())
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        return (new ResponseEntity(product.get(), HttpStatus.OK));
    }

    // Included in the Worker App guide
    @PostMapping("/prices")
    public ResponseEntity updatePrices(@RequestBody List<Double> newPrices) {

        List<Product> products = productRepository.findAll(PageRequest.of(0,12)).getContent();

        for (Product product : products)
            product.setPrice(newPrices.get(products.indexOf(product)));

        productRepository.saveAll(products);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity create(@RequestBody Product product) {
        return new ResponseEntity(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateById(@RequestBody Product product, @PathVariable int id) {

        if (!productRepository.findById(id).isPresent())
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        product.setId(id);

        return new ResponseEntity(productRepository.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        productRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/kill")
    public void kill() {
        System.exit(1);
    }
}
