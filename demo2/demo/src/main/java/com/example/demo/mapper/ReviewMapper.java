package com.example.demo.mapper;

import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    
    public Review toEntity(ReviewRequestDTO dto) {
        if (dto == null) return null;
        
        return new Review(
            dto.getVisitorId(),
            dto.getRestaurantId(),
            dto.getRating(),
            dto.getReviewText()
        );
    }
    
    public ReviewResponseDTO toResponseDTO(Review entity) {
        if (entity == null) return null;
        
        return new ReviewResponseDTO(
            entity.getVisitorId(),
            entity.getRestaurantId(),
            entity.getRating(),
            entity.getReviewText()
        );
    }
}
