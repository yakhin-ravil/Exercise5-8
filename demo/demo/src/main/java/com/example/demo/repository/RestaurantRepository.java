package com.example.demo.repository;

import com.example.demo.entity.Restaurant;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    
    public Restaurant save(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }
    
    public boolean remove(Restaurant restaurant) {
        return restaurants.remove(restaurant);
    }
    
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }
    
    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
