package com.whipstock.urlshortener.exceptions;

public class InvalidUrlException extends Exception {
  public InvalidUrlException(String message) {
    super(message);
  }
}
