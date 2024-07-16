package com.hyperform.application.marketplace.enums;

public enum NotificationsTypeEnum {
  EMAIL(0, "Email"),
  SMS(1, "SMS"),
  Telegram(2, "Telegram");

  private Integer key;
  private String value;

  NotificationsTypeEnum(int key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
