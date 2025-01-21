package com.whipstock.urlshortener.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {
  public final static int URL_KEY_LENGTH = 6;
  public String generate() {
    return RandomStringUtils.randomAlphanumeric(URL_KEY_LENGTH);
  }
}
