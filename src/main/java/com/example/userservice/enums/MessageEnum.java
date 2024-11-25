package com.example.userservice.enums;

public enum MessageEnum {
  USER_CREATED("User successfully created"),
  USER_FOUND("User found"),
  USER_NOT_FOUND("User not found"),
  USER_DELETED("User successfully deleted"),
  INTERNAL_ERROR("An internal error occurred");

  private final String message;

  MessageEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
