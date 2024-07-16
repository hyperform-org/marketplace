package com.hyperform.application.marketplace.mapper;

import com.hyperform.application.marketplace.domain.Product;
import com.hyperform.application.marketplace.model.dto.ProductDto;
import com.hyperform.application.marketplace.model.request.ProductRequest;
import com.hyperform.application.marketplace.model.response.ProductResponse;
import java.util.ArrayList;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {
  @Mapping(target = "title", source = "title", defaultValue = "")
  @Mapping(target = "description", source = "description", defaultValue = "")
  @Mapping(target = "category", source = "category", defaultValue = "")
  @Mapping(target = "subcategory", source = "subcategory", defaultValue = "")
  @Mapping(target = "price", source = "price", defaultValue = "0.0")
  @Mapping(target = "discount", source = "discount", defaultValue = "0.0")
  @Mapping(target = "tags", source = "tags", defaultExpression = "java(new String[0])")
  @Mapping(target = "imageUrls", source = "imageUrls", defaultExpression = "java(new java.util.ArrayList<>())")
  @Mapping(target = "seller", source = "seller", defaultValue = "")
  @Mapping(target = "buyer", source = "buyer", defaultValue = "")
  @Mapping(target = "itemId", source = "itemId", defaultValue = "")
  @Mapping(target = "createdDate", source = "createdDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "updatedDate", source = "updatedDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "createdBy", source = "createdBy", defaultValue = "")
  @Mapping(target = "updatedBy", source = "updatedBy", defaultValue = "")
  @Mapping(target = "status", source = "status")
  ProductDto documentToDto(Product workspace);

  @Mapping(target = "title", source = "title", defaultValue = "")
  @Mapping(target = "description", source = "description", defaultValue = "")
  @Mapping(target = "category", source = "category", defaultValue = "")
  @Mapping(target = "subcategory", source = "subcategory", defaultValue = "")
  @Mapping(target = "price", source = "price", defaultValue = "0.0")
  @Mapping(target = "discount", source = "discount", defaultValue = "0.0")
  @Mapping(target = "tags", source = "tags", defaultExpression = "java(new String[0])")
  @Mapping(target = "imageUrls", source = "imageUrls", defaultExpression = "java(new java.util.ArrayList<>())")
  @Mapping(target = "seller", source = "seller", defaultValue = "")
  @Mapping(target = "buyer", source = "buyer", defaultValue = "")
  @Mapping(target = "itemId", source = "itemId", defaultValue = "")
  @Mapping(target = "createdDate", source = "createdDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "updatedDate", source = "updatedDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "createdBy", source = "createdBy", defaultValue = "")
  @Mapping(target = "updatedBy", source = "updatedBy", defaultValue = "")
  @Mapping(target = "status", source = "status")
  ProductResponse documentToRes(Product workspace);

  @Mapping(target = "title", source = "title", defaultValue = "")
  @Mapping(target = "description", source = "description", defaultValue = "")
  @Mapping(target = "category", source = "category", defaultValue = "")
  @Mapping(target = "subcategory", source = "subcategory", defaultValue = "")
  @Mapping(target = "price", source = "price", defaultValue = "0.0")
  @Mapping(target = "discount", source = "discount", defaultValue = "0.0")
  @Mapping(target = "tags", source = "tags", defaultExpression = "java(new String[0])")
  @Mapping(target = "imageUrls", source = "imageUrls", defaultExpression = "java(new java.util.ArrayList<>())")
  @Mapping(target = "seller", source = "seller", defaultValue = "")
  @Mapping(target = "buyer", source = "buyer", defaultValue = "")
  @Mapping(target = "itemId", source = "itemId", defaultValue = "")
  @Mapping(target = "createdDate", source = "createdDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "updatedDate", source = "updatedDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "createdBy", source = "createdBy", defaultValue = "")
  @Mapping(target = "updatedBy", source = "updatedBy", defaultValue = "")
  @Mapping(target = "status", source = "status")
  Product dtoToDocument(ProductDto workspaceDto);

  @Mapping(target = "title", source = "title", defaultValue = "")
  @Mapping(target = "description", source = "description", defaultValue = "")
  @Mapping(target = "category", source = "category", defaultValue = "")
  @Mapping(target = "subcategory", source = "subcategory", defaultValue = "")
  @Mapping(target = "price", source = "price", defaultValue = "0.0")
  @Mapping(target = "discount", source = "discount", defaultValue = "0.0")
  @Mapping(target = "tags", source = "tags", defaultExpression = "java(new String[0])")
  @Mapping(target = "seller", source = "seller", defaultValue = "")
  @Mapping(target = "buyer", ignore = true)
  @Mapping(target = "itemId", source = "itemId", defaultValue = "")
  ProductDto reqToDto(ProductRequest req);

  @Mapping(target = "title", source = "title", defaultValue = "")
  @Mapping(target = "description", source = "description", defaultValue = "")
  @Mapping(target = "category", source = "category", defaultValue = "")
  @Mapping(target = "subcategory", source = "subcategory", defaultValue = "")
  @Mapping(target = "price", source = "price", defaultValue = "0.0")
  @Mapping(target = "discount", source = "discount", defaultValue = "0.0")
  @Mapping(target = "tags", source = "tags", defaultExpression = "java(new String[0])")
  @Mapping(target = "imageUrls", source = "imageUrls", defaultExpression = "java(new java.util.ArrayList<>())")
  @Mapping(target = "seller", source = "seller", defaultValue = "")
  @Mapping(target = "buyer", source = "buyer", defaultValue = "")
  @Mapping(target = "itemId", source = "itemId", defaultValue = "")
  @Mapping(target = "createdDate", source = "createdDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "updatedDate", source = "updatedDate", defaultExpression = "java(new java.util.Date())")
  @Mapping(target = "createdBy", source = "createdBy", defaultValue = "")
  @Mapping(target = "updatedBy", source = "updatedBy", defaultValue = "")
  @Mapping(target = "status", source = "status")
  ProductResponse dtoToRes(ProductDto workspaceDto);

  @AfterMapping
  default void handleNullValues(@MappingTarget ProductDto productDto) {
    if (productDto.getTitle() == null) {
      productDto.setTitle("");
    }
    if (productDto.getDescription() == null) {
      productDto.setDescription("");
    }
    if (productDto.getCategory() == null) {
      productDto.setCategory("");
    }
    if (productDto.getSubcategory() == null) {
      productDto.setSubcategory("");
    }
    if (productDto.getPrice() == null) {
      productDto.setPrice(0.0);
    }
    if (productDto.getDiscount() == null) {
      productDto.setDiscount(0.0);
    }
    if (productDto.getTags() == null) {
      productDto.setTags(new String[0]);
    }
    if (productDto.getImageUrls() == null) {
      productDto.setImageUrls(new ArrayList<>());
    }
    if (productDto.getSeller() == null) {
      productDto.setSeller("");
    }
    if (productDto.getBuyer() == null) {
      productDto.setBuyer("");
    }
    if (productDto.getItemId() == null) {
      productDto.setItemId("");
    }
  }
}
