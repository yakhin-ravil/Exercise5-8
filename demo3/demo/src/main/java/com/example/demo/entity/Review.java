package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Связь с посетителем (много отзывов - один посетитель)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;
    
    // Связь с рестораном (много отзывов - один ресторан)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @Column(name = "rating", nullable = false)
    private int rating;
    
    @Column(name = "review_text")
    private String reviewText; // может быть пустым
    
    // Конструкторы
    public Review() {}
    
    public Review(Visitor visitor, Restaurant restaurant, int rating, String reviewText) {
        this.visitor = visitor;
        this.restaurant = restaurant;
        this.rating = rating;
        this.reviewText = reviewText;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
    
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
    
    // Вспомогательные методы для совместимости с DTO
    public Long getVisitorId() { return visitor != null ? visitor.getId() : null; }
    public Long getRestaurantId() { return restaurant != null ? restaurant.getId() : null; }
    
    @Override
    public String toString() {
        return String.format("Review{id=%d, visitorId=%d, restaurantId=%d, rating=%d, reviewText='%s'}", 
                id, getVisitorId(), getRestaurantId(), rating, reviewText);
    }
}
