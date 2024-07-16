package com.hyperform.application.marketplace.domain;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "Marketplace_Product")
public class Product extends AbstractDocument {

  private static final long serialVersionUID = 8594028188865781993L;

  @NotNull(message = "Title is required")
  @Builder.Default
  private String title = "";

  @NotNull(message = "Description is required")
  @Builder.Default
  private String description = "";

  @NotNull(message = "Category is required")
  @Builder.Default
  private String category = "";

  @NotNull(message = "Sub category is required")
  @Builder.Default
  private String subcategory = "";

  @NotNull(message = "Price is required")
  @Builder.Default
  private Double price = 0.0;

  @NotNull(message = "Discount is required")
  @Builder.Default
  private Double discount = 0.0;

  @NotNull(message = "Tags urls is required")
  @Builder.Default
  private String[] tags = new String[0];

  @NotNull(message = "Image urls is required")
  @Builder.Default
  private List<String> imageUrls = new ArrayList<>();

  @NotNull(message = "Seller is required")
  @Field("seller")
  @Builder.Default
  private String seller = "";

  @Field("buyer")
  @Builder.Default
  private String buyer = "";

  @NotNull(message = "ItemId is required")
  @Field("item_id")
  @Builder.Default
  private String itemId = "";
}
