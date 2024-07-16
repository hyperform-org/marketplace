package com.hyperform.application.marketplace.domain;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public abstract class AbstractDocument implements Serializable {

  private static final long serialVersionUID = -3626241823584983764L;

  @Id
  private String id;

  @CreatedBy
  @NotNull(message = "CreatedBy is required")
  @Field("created_by")
  @Builder.Default
  private String createdBy = "";

  @CreatedDate
  @Field("created_date")
  @NotNull(message = "CreatedDate is required")
  @Builder.Default
  private Date createdDate = new Date();

  @Field("updated_date")
  @NotNull(message = "UpdatedDate is required")
  @LastModifiedDate
  private Date updatedDate = new Date();

  @Field("updated_by")
  @LastModifiedBy
  @NotNull(message = "UpdatedBy is required")
  @Builder.Default
  private String updatedBy = "";

  @Field("status")
  @NotNull(message = "Status is required")
  @Builder.Default
  private Integer status = 0;
}
