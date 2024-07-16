package com.hyperform.application.marketplace.enums;

public enum NotificationsStatusEnum {
  SEND(0, "SEND"),
  SEND_FAIL(1, "SEND"),
  RECEIVED(2, "RECEIVED"),
  SEEN(3, "SEEN"),
  REPLY(4, "REPLY");

  private Integer key;
  private String value;

  NotificationsStatusEnum(int key, String value) {
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
