package com.example.demo.entity;

public class Review {
    private Long visitorId;
    private Long restaurantId;
    private int rating;
    private String reviewText; // может быть пустым
    
    // Конструкторы
    public Review() {}
    
    public Review(Long visitorId, Long restaurantId, int rating, String reviewText) {
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
    
    @Override
    public String toString() {
        return "Review{visitorId=" + visitorId + ", restaurantId=" + restaurantId + 
               ", rating=" + rating + ", reviewText='" + reviewText + "'}";
    }
}
