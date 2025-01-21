package com.whipstock.urlshortener.services;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;

public interface UrlStoreService {
  void storeUrl(String url, String shortUrl) throws DuplicateKeyException;
}
