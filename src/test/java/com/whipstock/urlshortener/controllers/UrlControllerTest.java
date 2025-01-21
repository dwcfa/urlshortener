package com.whipstock.urlshortener.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import com.whipstock.urlshortener.model.DecodeResponse;
import com.whipstock.urlshortener.model.EncodeRequest;
import com.whipstock.urlshortener.model.EncodeResponse;
import com.whipstock.urlshortener.services.UrlEncodeService;
import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UrlControllerTest {

  public static final String URL = "https://www.google.com/";
  public static final String KEY = "key";
  public static final String NEW_URL = "http://short.est/key";
  @Autowired
  private UrlController controller;
  @MockBean
  private UrlEncodeService urlEncodeService;

  @Test
  @SneakyThrows
  public void encode_validUrl_returnsKey(){
    when(urlEncodeService.encode(URL))
        .thenReturn(CompletableFuture.completedFuture(KEY));
    final EncodeResponse encodeResponse = controller.encode(
        new EncodeRequest().setUrl(URL));
    assertEquals(NEW_URL, encodeResponse.getNewUrl());
  }

  @Test
  @SneakyThrows
  public void encode_duplicateKey_retries(){
    when(urlEncodeService.encode(URL))
        .thenThrow(new DuplicateKeyException("Duplicate key"))
        .thenReturn(CompletableFuture.completedFuture(KEY));
    final EncodeResponse encodeResponse = controller.encode(
        new EncodeRequest().setUrl(URL));
    assertEquals(NEW_URL, encodeResponse.getNewUrl());
    verify(urlEncodeService, times(2)).encode(URL);
  }

  @Test
  @SneakyThrows
  public void decode_validUrl_returnsNewUrl(){
    when(urlEncodeService.decode(KEY))
        .thenReturn(NEW_URL);
    final DecodeResponse decodeResponse = controller.decode(KEY);
    assertEquals(NEW_URL, decodeResponse.getUrl());
  }

}
