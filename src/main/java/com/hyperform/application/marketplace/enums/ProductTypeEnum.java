package com.hyperform.application.marketplace.enums;

public enum ProductTypeEnum {
  WORKSPACE("Workspace", 1),
  FORM_CREATOR("Form Creator", 2),
  FORM_SURVEY("Form survey", 3),
  FORM_VIEW("Form view", 4);

  private String type;
  private Integer code;

  ProductTypeEnum(String type, Integer code) {
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
