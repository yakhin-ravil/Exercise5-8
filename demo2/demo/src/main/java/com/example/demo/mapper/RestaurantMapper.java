package com.example.demo.mapper;

import com.example.demo.dto.request.RestaurantRequestDTO;
import com.example.demo.dto.response.RestaurantResponseDTO;
import com.example.demo.entity.Restaurant;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class RestaurantMapper {
    
    public Restaurant toEntity(RestaurantRequestDTO dto) {
        if (dto == null) return null;
        
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setCuisineType(dto.getCuisineType());
        restaurant.setAverageCheck(dto.getAverageCheck());
        restaurant.setRating(BigDecimal.ZERO);
        return restaurant;
    }
    
    public RestaurantResponseDTO toResponseDTO(Restaurant entity) {
        if (entity == null) return null;
        
        return new RestaurantResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCuisineType(),
            entity.getAverageCheck(),
            entity.getRating()
        );
    }
    
    public void updateEntity(Restaurant entity, RestaurantRequestDTO dto) {
        if (entity == null || dto == null) return;
        
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCuisineType(dto.getCuisineType());
        entity.setAverageCheck(dto.getAverageCheck());
    }
}
