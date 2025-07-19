package com.example.demo.entity;

import com.example.demo.enums.CuisineType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description; // может быть пустым
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type", nullable = false)
    private CuisineType cuisineType;
    
    @Column(name = "average_check", nullable = false, precision = 10, scale = 2)
    private BigDecimal averageCheck;
    
    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating; // средняя оценка пользователей
    
    // Связь с отзывами (один ресторан - много отзывов)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    // Конструкторы
    public Restaurant() {}
    
    public Restaurant(String name, String description, CuisineType cuisineType, 
                     BigDecimal averageCheck, BigDecimal rating) {
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.averageCheck = averageCheck;
        this.rating = rating;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public CuisineType getCuisineType() { return cuisineType; }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType = cuisineType; }
    
    public BigDecimal getAverageCheck() { return averageCheck; }
    public void setAverageCheck(BigDecimal averageCheck) { this.averageCheck = averageCheck; }
    
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    
    @Override
    public String toString() {
        return String.format("Restaurant{id=%d, name='%s', cuisineType=%s, averageCheck=%s, rating=%s}", 
                id, name, cuisineType, averageCheck, rating);
    }
}
