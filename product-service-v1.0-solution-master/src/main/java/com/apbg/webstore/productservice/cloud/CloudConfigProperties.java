package com.apbg.webstore.productservice.cloud;

import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Profile("cloud")
public interface CloudConfigProperties {
    Properties cloudProperties();
}