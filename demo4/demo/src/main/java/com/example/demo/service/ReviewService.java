package com.example.demo.service;

import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.entity.Review;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Visitor;
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
    
    @Autowired
    private VisitorService visitorService;
    
    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        // Получаем сущности посетителя и ресторана
        Visitor visitor = visitorService.getEntityById(dto.getVisitorId());
        Restaurant restaurant = restaurantService.getEntityById(dto.getRestaurantId());
        
        if (visitor == null || restaurant == null) {
            return null; // или выбросить исключение
        }
        
        // Создаем отзыв
        Review review = new Review(visitor, restaurant, dto.getRating(), dto.getReviewText());
        Review savedReview = reviewRepository.save(review);
        
        // Пересчитываем средний рейтинг ресторана
        updateRestaurantRating(dto.getRestaurantId());
        
        return reviewMapper.toResponseDTO(savedReview);
    }
    
    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public boolean deleteById(Long id) {
        if (reviewRepository.existsById(id)) {
            Review review = reviewRepository.findById(id).orElse(null);
            if (review != null) {
                Long restaurantId = review.getRestaurantId();
                reviewRepository.deleteById(id);
                updateRestaurantRating(restaurantId);
                return true;
            }
        }
        return false;
    }
    
    public List<ReviewResponseDTO> findByRestaurantId(Long restaurantId) {
    return reviewRepository.findByRestaurant_Id(restaurantId).stream()
            .map(reviewMapper::toResponseDTO)
            .collect(Collectors.toList());
}

    
    private void updateRestaurantRating(Long restaurantId) {
        Double averageRating = reviewRepository.findAverageRatingByRestaurantId(restaurantId);
        if (averageRating != null) {
            Restaurant restaurant = restaurantService.getEntityById(restaurantId);
            if (restaurant != null) {
                restaurant.setRating(BigDecimal.valueOf(averageRating));
                restaurantService.getEntityById(restaurantId); // Сохраняем через JPA
            }
        }
    }
    public boolean remove(ReviewRequestDTO dto) {
    // Предположим, что Review имеет id, либо ищем по visitorId и restaurantId и удаляем

    // Поиск отзыва по visitorId и restaurantId и удаление
    List<Review> reviews = reviewRepository.findAll();
    for (Review review : reviews) {
        if (review.getVisitor().getId().equals(dto.getVisitorId())
                && review.getRestaurant().getId().equals(dto.getRestaurantId())
                && review.getRating() == dto.getRating()) { // можно сравнивать и по тексту, если нужно
            reviewRepository.delete(review);
            updateRestaurantRating(dto.getRestaurantId());
            return true;
        }
    }
    return false;
}

}
