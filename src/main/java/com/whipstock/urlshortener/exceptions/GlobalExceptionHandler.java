package com.whipstock.urlshortener.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler(value = {TaskRejectedException.class})
  public ResponseEntity<TooManyRequestsException> handleTaskRejectedException(Exception ex, WebRequest request) {
    log.error("DuplicateKeyException check RandomGenerator "
        + "if you see this more than once in a week, there is a problem", ex);
    return new ResponseEntity<>(new TooManyRequestsException(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()), HttpStatus.TOO_MANY_REQUESTS);
  }

  @ExceptionHandler(value = {InvalidUrlException.class})
  public ResponseEntity<InvalidUrlException> handleInvalidUrlException(InvalidUrlException ex, WebRequest request) {
    return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Exception> handleException(Exception ex, WebRequest request) {
    log.error("Exception caught at GlobalExceptionHandler. ", ex);
    return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
