package com.whipstock.urlshortener.services;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public interface UrlEncodeService {
  CompletableFuture<String> encode(String url) throws ExecutionException,
      InterruptedException, DuplicateKeyException;
  String decode(String key);
}
