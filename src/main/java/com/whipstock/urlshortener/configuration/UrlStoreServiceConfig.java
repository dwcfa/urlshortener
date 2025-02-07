package com.whipstock.urlshortener.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("url-store-service")
public class UrlStoreServiceConfig {
  private int initialCapacity;
}
