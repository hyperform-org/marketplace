package com.hyperform.application.marketplace.model.response;

import com.hyperform.application.marketplace.model.dto.AbstractDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BaseResponse extends AbstractDto {

  private Date timestamp;
  private Integer status;
  private String message;
  private BaseErrorResponse errors;
  private String requestId;
  private String error;

  public BaseResponse() {
    this.timestamp = new Date();
    this.status = HttpStatus.OK.value();
    this.errors = new BaseErrorResponse();
  }

  public BaseResponse(Integer status, BaseErrorResponse errors) {
    this.timestamp = new Date();
    this.status = status;
    this.errors = errors;
  }
}
