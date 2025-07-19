package com.example.demo.mapper;

//import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    
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
