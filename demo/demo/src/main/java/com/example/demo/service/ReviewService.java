package com.example.demo.service;

import com.example.demo.entity.Review;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    public Review save(Review review) {
        Review savedReview = reviewRepository.save(review);
        updateRestaurantRating(review.getRestaurantId());
        return savedReview;
    }
    
    public boolean remove(Review review) {
        boolean removed = reviewRepository.remove(review);
        if (removed) {
            updateRestaurantRating(review.getRestaurantId());
        }
        return removed;
    }
    
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
    
    private void updateRestaurantRating(Long restaurantId) {
        List<Review> restaurantReviews = reviewRepository.findAll().stream()
                .filter(review -> review.getRestaurantId().equals(restaurantId))
                .toList();
        
        if (!restaurantReviews.isEmpty()) {
            double averageRating = restaurantReviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            
            Restaurant restaurant = restaurantService.findById(restaurantId);
            if (restaurant != null) {
                restaurant.setRating(BigDecimal.valueOf(averageRating));
            }
        }
    }
}
