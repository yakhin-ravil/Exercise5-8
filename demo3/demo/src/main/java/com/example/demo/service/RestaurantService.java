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
    
    public RestaurantResponseDTO save(RestaurantRequestDTO dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDTO(savedRestaurant);
    }
    
    public List<RestaurantResponseDTO> findAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public RestaurantResponseDTO findById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toResponseDTO)
                .orElse(null);
    }
    
    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurantMapper.updateEntity(restaurant, dto);
                    Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
                    return restaurantMapper.toResponseDTO(updatedRestaurant);
                })
                .orElse(null);
    }
    
    public boolean deleteById(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Вспомогательный метод для получения сущности
    public Restaurant getEntityById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }
}
