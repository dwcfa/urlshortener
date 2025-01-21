package com.whipstock.urlshortener.exceptions;

public class DuplicateKeyException extends Exception {

  public DuplicateKeyException(String message) {
    super(message);
  }
}
