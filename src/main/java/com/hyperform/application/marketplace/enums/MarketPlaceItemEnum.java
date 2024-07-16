package com.hyperform.application.marketplace.enums;

public enum MarketPlaceItemEnum {
  SELLING(1),
  CANCEL(2),
  WITHDRAW(3),
  SOLD(4);

  private Integer key;

  MarketPlaceItemEnum(Integer key) {
    this.key = key;
  }

  public Integer getKey() {
    return key;
  }
}
