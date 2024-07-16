package com.hyperform.application.marketplace.exceptions;

import com.hyperform.application.marketplace.enums.ErrorMessageEnum;
import com.hyperform.application.marketplace.exceptions.UnauthorizedException;
import com.hyperform.application.marketplace.model.response.BaseErrorResponse;
import com.hyperform.application.marketplace.model.response.BaseResponse;
import com.hyperform.application.marketplace.model.response.ValidationErrorResponse;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionsHandler {

  private static final Logger logger = LogManager.getLogger(AppExceptionsHandler.class);

  @ExceptionHandler(value = { NotFoundException.class })
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
    try {
      BaseResponse errorMessage = getErrorMessage(ex.getMessage());
      errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    } catch (Exception error) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ExceptionHandler(value = { UnauthorizedException.class })
  public ResponseEntity<Object> handleUnauthorizedException(NotFoundException ex, WebRequest request) {
    try {
      BaseResponse errorMessage = getErrorMessage(ex.getMessage());
      errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    } catch (Exception error) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ExceptionHandler(value = { MethodArgumentNotValidException.class })
  public ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
    Map<String, String> attributes = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      attributes.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
    baseErrorResponse.setAttributes(attributes);

    BaseResponse baseResponse = new BaseResponse(HttpStatus.BAD_REQUEST.value(), baseErrorResponse);
    baseResponse.setMessage("Validation failed");

    return new ResponseEntity<>(baseResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
    try {
      BaseResponse errorMessage = getErrorMessage(ex.getMessage());
      errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    } catch (Exception error) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private BaseResponse getErrorMessage(String message) {
    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
    Map<String, String> errorMap = new LinkedHashMap<>();
    errorMap.put("errorMessage", message);
    baseErrorResponse.setServer(errorMap);

    BaseResponse baseResponse = new BaseResponse(HttpStatus.BAD_REQUEST.value(), baseErrorResponse);
    return baseResponse;
  }
  //  @ExceptionHandler(value = { Exception.class })
  //  public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
  //    logger.error("Handling general exception: ", ex);
  //    BaseResponse errorMessage = getErrorMessage(ex.getMessage());
  //    errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
  //    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  //  }

}
