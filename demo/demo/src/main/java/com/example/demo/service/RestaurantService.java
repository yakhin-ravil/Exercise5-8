package com.example.demo.service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    
    public boolean remove(Restaurant restaurant) {
        return restaurantRepository.remove(restaurant);
    }
    
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
    
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id);
    }
}
