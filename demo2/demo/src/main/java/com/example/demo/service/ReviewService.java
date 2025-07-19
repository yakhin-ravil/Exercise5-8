package com.example.demo.service;

import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.entity.Review;
import com.example.demo.entity.Restaurant;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private ReviewMapper reviewMapper;
    
    @Autowired
    private RestaurantService restaurantService;
    
    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        Review savedReview = reviewRepository.save(review);
        updateRestaurantRating(dto.getRestaurantId());
        return reviewMapper.toResponseDTO(savedReview);
    }
    
    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public boolean remove(ReviewRequestDTO dto) {
        List<Review> reviews = reviewRepository.findAll();
        for (Review review : reviews) {
            if (review.getVisitorId().equals(dto.getVisitorId()) && 
                review.getRestaurantId().equals(dto.getRestaurantId())) {
                boolean removed = reviewRepository.remove(review);
                if (removed) {
                    updateRestaurantRating(dto.getRestaurantId());
                }
                return removed;
            }
        }
        return false;
    }
    
    private void updateRestaurantRating(Long restaurantId) {
    List<Review> restaurantReviews = reviewRepository.findAll().stream()
            .filter(review -> review.getRestaurantId().equals(restaurantId))
            .collect(Collectors.toList());

    if (!restaurantReviews.isEmpty()) {
        double averageRating = restaurantReviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        Restaurant restaurant = restaurantService.getEntityById(restaurantId);
        if (restaurant != null) {
            restaurant.setRating(BigDecimal.valueOf(averageRating));
        }
    }
}

}
