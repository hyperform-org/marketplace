package com.hyperform.application.marketplace.enums;

public enum ErrorMessageEnum {
  BAD_REQUEST_EXCEPTION(400, "Bad Request Exception"),
  USER_EXIST_EXCEPTION(404, "User Exist Exception"),
  USER_EMAIL_EXIST_EXCEPTION(404, "User Email Exist Exception"),
  USERNAME_EXIST_EXCEPTION(404, "Username Exist Exception"),
  USERNAME_NOT_EXIST_EXCEPTION(404, "Username Not Exist Exception"),
  USER_NOT_EXIST_EXCEPTION(400, "User Not Exist Exception"),
  USER_NOT_ACTIVATE_EXCEPTION(400, "User Not Activate Exception"),
  USER_NOT_IN_ACTIVATE_EXCEPTION(400, "User Not In Activate Exception"),
  RECORD_NOT_FOUND_EXCEPTION(400, "Record Not found Exception"),
  EMAIL_NOT_REGISTER_EXCEPTION(404, "Email Not Register Exception"),
  USER_NOT_IN_PENDING_STATUS__EXCEPTION(404, "User Not In Pending Exception"),
  VERIFY_KEY_NOT_EXIST__EXCEPTION(404, "Verify Key Not Exist Exception"),
  NOTIFICATION_RECORD_NOT_FOUND_EXCEPTION(404, "Notification Record Not found Exception");

  private Integer status;
  private String message;

  ErrorMessageEnum(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public Integer getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
