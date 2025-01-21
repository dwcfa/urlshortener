package com.whipstock.urlshortener.exceptions;

public class TooManyRequestsException extends Exception {

  public TooManyRequestsException(String message) {
    super(message);
  }
}
