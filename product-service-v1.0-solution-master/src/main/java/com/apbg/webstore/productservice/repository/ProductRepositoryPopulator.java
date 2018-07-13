package com.apbg.webstore.productservice.repository;

import com.apbg.webstore.productservice.domain.Product;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.data.repository.init.ResourceReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryPopulator implements ApplicationListener<ApplicationReadyEvent> {
    private final ResourceReader resourceReader;
    private final Resource sourceData;
    private ProductRepository productRepository;
    private ApplicationContext applicationContext;

    public ProductRepositoryPopulator(ProductRepository productRepository) {
        this.productRepository = productRepository;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        resourceReader = new Jackson2ResourceReader(objectMapper);
        sourceData = new ClassPathResource("products.json");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            List<Product> entity = (List<Product>) resourceReader.readFrom(sourceData, getClass().getClassLoader());
            productRepository.saveAll(entity);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
