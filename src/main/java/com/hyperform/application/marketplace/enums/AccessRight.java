package com.hyperform.application.marketplace.enums;

public enum AccessRight {
  READ("Read"),
  RESPOND("Respond"),
  EDIT("Edit"),
  DELETE("Delete"),
  MANAGE("Manage");

  private String description;

  AccessRight(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
