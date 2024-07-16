package com.hyperform.application.marketplace.model.response;

import com.hyperform.application.marketplace.model.dto.ProductDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MarketplaceItemResponse {

  private String requestId;
  List<ProductDto> items;
}
