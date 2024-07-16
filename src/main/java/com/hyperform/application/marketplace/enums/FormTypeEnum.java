package com.hyperform.application.marketplace.enums;

public enum FormTypeEnum {
  FORM("Form"),
  QUIZ("Quiz");

  private String type;

  FormTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
