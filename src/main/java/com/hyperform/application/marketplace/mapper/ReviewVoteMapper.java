package com.hyperform.application.marketplace.mapper;

import com.hyperform.application.marketplace.domain.Review;
import com.hyperform.application.marketplace.domain.ReviewVote;
import com.hyperform.application.marketplace.model.dto.ReviewVoteDto;
import com.hyperform.application.marketplace.model.request.ReviewVoteRequest;
import com.hyperform.application.marketplace.model.response.ReviewVoteResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewVoteMapper {
  @Mapping(target = "id", source = "id")
  @Mapping(target = "createdDate", source = "createdDate")
  ReviewVoteDto documentToDto(ReviewVote reviewVote);

  @Mapping(target = "id", source = "id")
  ReviewVoteResponse documentToRes(Review review);

  Review dtoToDocument(ReviewVoteDto dto);

  ReviewVoteDto reqToDto(ReviewVoteRequest req);

  ReviewVoteResponse dtoToRes(ReviewVoteDto dto);
}
