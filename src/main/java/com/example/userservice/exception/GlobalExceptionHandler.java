package com.example.userservice.exception;

import com.example.userservice.utils.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponsePayload<Object> handleCustomException(CustomException ex) {
    ResponsePayload<Object> response = new ResponsePayload<>();
    response.setResponse(ex.getStatus(), ex.getMessage(), null);
    return response;
  }

  @ExceptionHandler(Exception.class)
  public ResponsePayload<Object> handleGeneralException(Exception ex) {
    ResponsePayload<Object> response = new ResponsePayload<>();
    response.setResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    return response;
  }
}
