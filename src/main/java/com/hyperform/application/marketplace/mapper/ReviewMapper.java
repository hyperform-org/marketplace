package com.hyperform.application.marketplace.mapper;

import com.hyperform.application.marketplace.domain.Review;
import com.hyperform.application.marketplace.model.dto.ReviewDto;
import com.hyperform.application.marketplace.model.request.ReviewRequest;
import com.hyperform.application.marketplace.model.response.ReviewResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewMapper {
  @Mapping(target = "id", source = "id")
  ReviewDto documentToDto(Review review);

  @Mapping(target = "id", ignore = true)
  ReviewResponse documentToRes(Review review);

  Review dtoToDocument(ReviewDto dto);

  ReviewDto reqToDto(ReviewRequest req);

  ReviewResponse dtoToRes(ReviewDto dto);
}
