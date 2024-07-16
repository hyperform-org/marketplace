package com.hyperform.application.marketplace.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto extends AbstractDto {

  public String title;
  private String description;
  private String category;
  private String subcategory;
  private Double price;
  private Double discount;
  private List<String> imageUrls = new ArrayList<>();
  private String seller;
  private String buyer;
  private String[] tags;
  private String itemId;
}
