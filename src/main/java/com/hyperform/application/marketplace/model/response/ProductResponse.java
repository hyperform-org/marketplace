package com.hyperform.application.marketplace.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class ProductResponse extends BaseResponse {

  private String id;
  public String title;
  private String description;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String category;
  private String subcategory;
  private Double price;
  private Double discount;
  private Integer status;
  private List<String> imageUrls = new ArrayList<>();
  private String seller;
  private String buyer;
  private String[] tags;
  private String itemId;
}
