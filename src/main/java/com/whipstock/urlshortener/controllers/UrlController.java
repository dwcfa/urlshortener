package com.whipstock.urlshortener.controllers;

import com.whipstock.urlshortener.exceptions.DuplicateKeyException;
import com.whipstock.urlshortener.model.DecodeResponse;
import com.whipstock.urlshortener.model.EncodeRequest;
import com.whipstock.urlshortener.model.EncodeResponse;
import com.whipstock.urlshortener.services.UrlEncodeService;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
/*
This class has some retry logic in case a duplicate key happens
The chance of this happening is 37^6 which is 2,565,726,409
IE better chance of hitting the lottery.
 */
public class UrlController {
  private final UrlEncodeService urlEncodeService;

  @PostMapping(value = "/encode", produces = "application/json")
  public EncodeResponse encode(@RequestBody EncodeRequest encode)
      throws ExecutionException, InterruptedException, DuplicateKeyException {
    try {
      return new EncodeResponse()
          .setKey(urlEncodeService.encode(encode.getUrl()).get());
    } catch (DuplicateKeyException e) {
      log.error("DuplicateKeyException check RandomGenerator "
          + "if you see this more than once in a week,", e);
      return new EncodeResponse()
          .setKey(urlEncodeService.encode(encode.getUrl()).get());
    }
  }

  @GetMapping(value = "/decode", produces = "application/json")
  public DecodeResponse decode(@RequestParam String key) {
    return new DecodeResponse().setUrl(urlEncodeService.decode(key));
  }
}
