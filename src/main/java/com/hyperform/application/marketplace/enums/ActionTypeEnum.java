package com.hyperform.application.marketplace.enums;

public enum ActionTypeEnum {
  LIKED("Like", 1),
  DISLIKED("Disliked", 2);

  private String type;
  private Integer code;

  ActionTypeEnum(String type, Integer code) {
    this.type = type;
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public Integer getCode() {
    return code;
  }
}
