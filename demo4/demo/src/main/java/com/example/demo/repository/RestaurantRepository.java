package com.example.demo.repository;

import com.example.demo.entity.Restaurant;
import com.example.demo.enums.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    // Дополнительные методы поиска (по желанию)
    List<Restaurant> findByCuisineType(CuisineType cuisineType);
    // List<Restaurant> findByNameContainingIgnoreCase(String name);
}
