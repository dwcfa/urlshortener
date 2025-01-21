package com.whipstock.urlshortener.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.whipstock.urlshortener.configuration.UrlStoreServiceConfig;
import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlStoreServiceLocalMemoryImplTest {
  private UrlStoreServiceLocalMemoryImpl urlStoreService;
  @BeforeEach
  void setUp() {
    UrlStoreServiceConfig urlStoreServiceConfig;
    urlStoreServiceConfig = new UrlStoreServiceConfig();
    urlStoreServiceConfig.setInitialCapacity(1000);
    urlStoreService = new UrlStoreServiceLocalMemoryImpl(urlStoreServiceConfig) ;
  }

  @SneakyThrows
  @Test
  void testStoreUrl_ValidKeyValue_Success() {
    urlStoreService.storeUrl("myUrlKey", "myUrlValue");
    assert urlStoreService.getUrl("myUrlKey").equals("myUrlValue");
  }

  @SneakyThrows
  @Test
  void testStoreUrl_ValidKeyValueDuplicate_ThrowsDuplicateKeyException() {
    urlStoreService.storeUrl("myUrlKey", "myUrlValue");
    assertThrows(
        DuplicateKeyException.class, () -> urlStoreService.storeUrl("myUrlKey", "somethingElse"));
  }
}
