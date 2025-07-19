package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();
    
    public Review save(Review review) {
        reviews.add(review);
        return review;
    }
    
    public boolean remove(Review review) {
        return reviews.remove(review);
    }
    
    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }
    
    public Review findById(Long restaurantId) {
        return reviews.stream()
                .filter(review -> review.getRestaurantId().equals(restaurantId))
                .findFirst()
                .orElse(null);
    }
}
