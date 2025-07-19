package com.example.demo.dto.response;

import com.example.demo.enums.CuisineType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class RestaurantResponseDTO {
    
    @Schema(description = "ID ресторана", example = "1")
    private Long id;
    
    @Schema(description = "Название ресторана", example = "Pasta Paradise")
    private String name;
    
    @Schema(description = "Описание ресторана", example = "Лучшая итальянская кухня")
    private String description;
    
    @Schema(description = "Тип кухни", example = "ITALIAN")
    private CuisineType cuisineType;
    
    @Schema(description = "Средний чек", example = "1500.00")
    private BigDecimal averageCheck;
    
    @Schema(description = "Рейтинг ресторана", example = "4.5")
    private BigDecimal rating;
    
    // Конструкторы
    public RestaurantResponseDTO() {}
    
    public RestaurantResponseDTO(Long id, String name, String description, CuisineType cuisineType, 
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
}
