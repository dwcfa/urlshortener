package com.whipstock.urlshortener.services.impl;

import com.whipstock.urlshortener.configuration.UrlStoreServiceConfig;
import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import com.whipstock.urlshortener.services.UrlStoreService;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
/*
Any changes made to this class should also be made to
UrlStoreServiceLocalMemorySlowMovingImpl
 */
@Service
public class UrlStoreServiceLocalMemoryImpl implements UrlStoreService {

  private final ConcurrentHashMap<String, String> urlMap;

  public UrlStoreServiceLocalMemoryImpl(UrlStoreServiceConfig urlStoreServiceConfig) {
    this.urlMap = new ConcurrentHashMap<>(urlStoreServiceConfig.getInitialCapacity());
  }

  public String storeUrl(String key, String url) throws DuplicateKeyException {
    String existing = urlMap.putIfAbsent(key, url);
    if (existing != null) {
      throw new DuplicateKeyException("Url already exists");
    }
    return key;
  }

  public String getUrl(String shortUrl) {
    return urlMap.get(shortUrl);
  }
}
