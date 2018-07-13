package com.apbg.webstore.productservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class AppDetails {
    private String appName;
    private String instanceIndex;
    private String serviceUrl;
    private String appVersion;
    private Map<String, String> productVars;
}
