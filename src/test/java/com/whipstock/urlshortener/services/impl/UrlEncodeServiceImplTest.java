package com.whipstock.urlshortener.services.impl;

import static com.whipstock.urlshortener.util.RandomGenerator.URL_KEY_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.whipstock.urlshortener.services.UrlEncodeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskRejectedException;

@SpringBootTest
public class UrlEncodeServiceImplTest {
  @Autowired
  private UrlEncodeService urlEncodeService;

  @Test
  @SneakyThrows
  public void encode_validURL_Success() {
    String key = urlEncodeService.encode("https://www.google.com").get();
    final String decode = urlEncodeService.decode(key);
    assertEquals(URL_KEY_LENGTH, key.length());
    assertEquals("https://www.google.com", decode);
  }

  @Test
  public void encode_validURL_ErrorTooManyRequests() {
    assertThrows(
        TaskRejectedException.class, this::tooManyRequests);
  }

  private void tooManyRequests() throws Exception {
    for (int i = 0; i < 100; i++) {
      urlEncodeService.encode("https://www.google.com");
    }
  }
}
