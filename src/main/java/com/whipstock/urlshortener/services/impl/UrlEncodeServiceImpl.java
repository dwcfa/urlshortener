package com.whipstock.urlshortener.services.impl;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import com.whipstock.urlshortener.services.UrlEncodeService;
import com.whipstock.urlshortener.services.UrlStoreService;
import com.whipstock.urlshortener.util.RandomGenerator;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UrlEncodeServiceImpl implements UrlEncodeService {
  private final UrlStoreService urlStoreService;
  private final RandomGenerator randomGenerator;

  @Async("urlThreadPool")
  public CompletableFuture<String> encode(String url) throws DuplicateKeyException {
    String key = randomGenerator.generate();
    urlStoreService.storeUrl(key,url);
    return CompletableFuture.completedFuture(key);
  }

  public String decode(String key) {
    return urlStoreService.getUrl(key);
  }
}
