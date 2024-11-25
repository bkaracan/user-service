package com.example.userservice.enums;

import org.springframework.http.HttpStatus;

public enum ResponseEnum {
  SUCCESS(HttpStatus.OK),
  CREATED(HttpStatus.CREATED),
  BAD_REQUEST(HttpStatus.BAD_REQUEST),
  NOT_FOUND(HttpStatus.NOT_FOUND),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

  private final HttpStatus status;

  ResponseEnum(HttpStatus status) {
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
