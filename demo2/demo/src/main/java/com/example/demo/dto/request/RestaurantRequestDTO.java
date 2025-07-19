package com.example.demo.dto.request;

import com.example.demo.enums.CuisineType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class RestaurantRequestDTO {
    
    @Schema(description = "Название ресторана", example = "Pasta Paradise", required = true)
    private String name;
    
    @Schema(description = "Описание ресторана", example = "Лучшая итальянская кухня")
    private String description;
    
    @Schema(description = "Тип кухни", example = "ITALIAN", required = true)
    private CuisineType cuisineType;
    
    @Schema(description = "Средний чек", example = "1500.00", required = true)
    private BigDecimal averageCheck;
    
    // Конструкторы
    public RestaurantRequestDTO() {}
    
    public RestaurantRequestDTO(String name, String description, CuisineType cuisineType, BigDecimal averageCheck) {
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.averageCheck = averageCheck;
    }
    
    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public CuisineType getCuisineType() { return cuisineType; }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType = cuisineType; }
    
    public BigDecimal getAverageCheck() { return averageCheck; }
    public void setAverageCheck(BigDecimal averageCheck) { this.averageCheck = averageCheck; }
}
