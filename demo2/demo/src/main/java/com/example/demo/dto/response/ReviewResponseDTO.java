package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewResponseDTO {
    
    @Schema(description = "ID посетителя", example = "1")
    private Long visitorId;
    
    @Schema(description = "ID ресторана", example = "1")
    private Long restaurantId;
    
    @Schema(description = "Оценка от 1 до 5", example = "5")
    private int rating;
    
    @Schema(description = "Текст отзыва", example = "Отличная паста!")
    private String reviewText;
    
    // Конструкторы
    public ReviewResponseDTO() {}
    
    public ReviewResponseDTO(Long visitorId, Long restaurantId, int rating, String reviewText) {
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.reviewText = reviewText;
    }
    
    // Геттеры и сеттеры
    public Long getVisitorId() { return visitorId; }
    public void setVisitorId(Long visitorId) { this.visitorId = visitorId; }
    
    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}
