package com.apbg.webstore.productservice.cloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
@Profile("!cloud")
public class LocalCloudProps implements CloudConfigProperties {
    @Override
    public Properties cloudProperties() {
        return new Properties();
    }
}
