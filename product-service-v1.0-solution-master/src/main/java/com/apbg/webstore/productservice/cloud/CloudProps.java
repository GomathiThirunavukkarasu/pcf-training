package com.apbg.webstore.productservice.cloud;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
@Profile("cloud")
public class CloudProps implements CloudConfigProperties {

    @Bean
    public Properties cloudProperties() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        return cloud.getCloudProperties();
    }
}