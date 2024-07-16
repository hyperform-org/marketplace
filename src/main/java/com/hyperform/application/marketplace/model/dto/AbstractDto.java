package com.hyperform.application.marketplace.model.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Data
public abstract class AbstractDto implements Serializable {

  private String id;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String deletedBy;
  private Integer status;
  private Date deletedDate;
}
