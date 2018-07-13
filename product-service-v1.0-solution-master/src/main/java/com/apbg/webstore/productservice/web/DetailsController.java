package com.apbg.webstore.productservice.web;

import com.apbg.webstore.productservice.cloud.CloudConfigProperties;
import com.apbg.webstore.productservice.domain.AppDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
public class DetailsController {
    private CloudConfigProperties config;

    @Value("${cloud.services.mysql.connection.jdbcurl:#{'jdbc:h2:mem:test'}}")
    private String serviceUrl;

    @Value("${PRODUCTS_A:#{null}}")
    private String productsA;

    @Value("${PRODUCTS_B:#{null}}")
    private String productsB;

    @Value("${PRODUCTS_C:#{null}}")
    private String productsC;

    @Value("${APP_VERSION:#{null}}")
    private String appVersion;

    public DetailsController(CloudConfigProperties config) {
        this.config = config;
    }

    @RequestMapping("/app-details")
    public AppDetails info() {
        Properties cloudProperties = config.cloudProperties();
        Map<String, String> map = new HashMap<>();
        if (productsA != null) {
            map.put("PRODUCTS_A", productsA);
        }
        if (productsB != null) {
            map.put("PRODUCTS_B", productsB);
        }
        if (productsC != null) {
            map.put("PRODUCTS_C", productsC);
        }
        if (map.isEmpty()) {
            map = null;
        }
        return AppDetails.builder()
                .appName(cloudProperties.getProperty("cloud.application.name"))
                .instanceIndex(cloudProperties.get("cloud.application.instance_index").toString())
                .serviceUrl(serviceUrl)
                .appVersion(this.appVersion)
                .productVars(map)
                .build();
    }
}
