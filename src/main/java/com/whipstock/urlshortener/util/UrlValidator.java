package com.whipstock.urlshortener.util;

import com.whipstock.urlshortener.exceptions.InvalidUrlException;
import org.springframework.stereotype.Component;

@Component
public class UrlValidator {
  private static final org.apache.commons.validator.routines.UrlValidator VALIDATOR = new org.apache.commons.validator.routines.UrlValidator();

  public void validate(String url) throws InvalidUrlException {
    if (!VALIDATOR.isValid(url)) {
      throw new InvalidUrlException("Invalid URL: " + url);
    }
  }
}
