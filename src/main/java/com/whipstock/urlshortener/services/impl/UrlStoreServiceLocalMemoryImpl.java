package com.whipstock.urlshortener.services.impl;

import com.whipstock.urlshortener.configuration.UrlStoreServiceConfig;
import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import java.util.concurrent.ConcurrentHashMap;


public class UrlStoreServiceLocalMemoryImpl {

  private final ConcurrentHashMap<String, String> urlMap;

  public UrlStoreServiceLocalMemoryImpl(UrlStoreServiceConfig urlStoreServiceConfig) {
    this.urlMap = new ConcurrentHashMap<>(urlStoreServiceConfig.getInitialCapacity());
  }

  public void storeUrl(String url, String shortUrl) throws DuplicateKeyException {
    String existing = urlMap.putIfAbsent(url, shortUrl);
    if (existing != null) {
      throw new DuplicateKeyException("Url already exists");
    }
  }

  public String getUrl(String shortUrl) {
    return urlMap.get(shortUrl);
  }
}
