package com.hyperform.application.marketplace.model.request;

import jakarta.validation.constraints.*;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateProductRequest {

  @NotEmpty(message = "Title is required")
  private String title;

  @NotEmpty(message = "Description is required")
  private String description;

  @NotEmpty(message = "Category is required")
  private String category;

  @NotEmpty(message = "Subcategory is required")
  private String subcategory;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be greater than 0")
  private Double price;

  @NotNull(message = "Discount is required")
  @Min(value = 0, message = "Discount must be at least 0")
  @Max(value = 100, message = "Discount must be at most 100")
  private Double discount;

  @Size(min = 1, max = 10, message = "You must upload between 1 and 10 images")
  private List<@NotEmpty(message = "Image URL is required") String> imageUrls;
}
