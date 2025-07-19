package com.example.demo.service;

import com.example.demo.dto.request.RestaurantRequestDTO;
import com.example.demo.dto.response.RestaurantResponseDTO;
import com.example.demo.entity.Restaurant;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private RestaurantMapper restaurantMapper;
    
    private Long nextId = 1L;
    
    public RestaurantResponseDTO save(RestaurantRequestDTO dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurant.setId(nextId++);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDTO(savedRestaurant);
    }
    
    public List<RestaurantResponseDTO> findAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public RestaurantResponseDTO findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        return restaurantMapper.toResponseDTO(restaurant);
    }
    
    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            restaurantMapper.updateEntity(restaurant, dto);
            return restaurantMapper.toResponseDTO(restaurant);
        }
        return null;
    }
    
    public boolean deleteById(Long id) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(id)) {
                return restaurantRepository.remove(restaurant);
            }
        }
        return false;
    }

    public Restaurant getEntityById(Long id) {
        return restaurantRepository.findById(id);
    }

}
