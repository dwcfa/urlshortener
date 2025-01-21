package com.whipstock.urlshortener.services;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public interface UrlStoreService {
  String storeUrl(String key, String shortUrl) throws DuplicateKeyException;
  String getUrl(String key);
}
