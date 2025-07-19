package com.example.demo.entity;

import com.example.demo.enums.CuisineType;
import java.math.BigDecimal;

public class Restaurant {
    private Long id;
    private String name;
    private String description; // может быть пустым
    private CuisineType cuisineType;
    private BigDecimal averageCheck;
    private BigDecimal rating; // средняя оценка пользователей
    
    // Конструкторы
    public Restaurant() {}
    
    public Restaurant(Long id, String name, String description, CuisineType cuisineType, 
                     BigDecimal averageCheck, BigDecimal rating) {
        this.id = id;
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
    
    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "', cuisineType=" + cuisineType + 
               ", averageCheck=" + averageCheck + ", rating=" + rating + "}";
    }
}
