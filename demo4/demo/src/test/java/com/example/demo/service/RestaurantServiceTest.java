package com.example.demo.service;

import com.example.demo.dto.request.RestaurantRequestDTO;
import com.example.demo.dto.response.RestaurantResponseDTO;
import com.example.demo.entity.Restaurant;
import com.example.demo.enums.CuisineType;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant restaurant;
    private RestaurantRequestDTO restaurantRequestDTO;
    private RestaurantResponseDTO restaurantResponseDTO;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant("Pasta Paradise", "Лучшая итальянская кухня",
                CuisineType.ITALIAN, BigDecimal.valueOf(1500), BigDecimal.ZERO);
        restaurant.setId(1L);

        restaurantRequestDTO = new RestaurantRequestDTO("Pasta Paradise", "Лучшая итальянская кухня",
                CuisineType.ITALIAN, BigDecimal.valueOf(1500));
        
        restaurantResponseDTO = new RestaurantResponseDTO(1L, "Pasta Paradise", "Лучшая итальянская кухня",
                CuisineType.ITALIAN, BigDecimal.valueOf(1500), BigDecimal.ZERO);
    }

    @Test
    void testSave() {
        // Given
        when(restaurantMapper.toEntity(restaurantRequestDTO)).thenReturn(restaurant);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantMapper.toResponseDTO(restaurant)).thenReturn(restaurantResponseDTO);

        // When
        RestaurantResponseDTO result = restaurantService.save(restaurantRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(restaurantResponseDTO.getId(), result.getId());
        assertEquals(restaurantResponseDTO.getName(), result.getName());
        verify(restaurantRepository).save(any(Restaurant.class));
        verify(restaurantMapper).toEntity(restaurantRequestDTO);
        verify(restaurantMapper).toResponseDTO(restaurant);
    }

    @Test
    void testFindAll() {
        // Given
        List<Restaurant> restaurants = Arrays.asList(restaurant);
        
        when(restaurantRepository.findAll()).thenReturn(restaurants);
        when(restaurantMapper.toResponseDTO(restaurant)).thenReturn(restaurantResponseDTO);

        // When
        List<RestaurantResponseDTO> result = restaurantService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(restaurantResponseDTO.getName(), result.get(0).getName());
        verify(restaurantRepository).findAll();
    }

    @Test
    void testFindById() {
        // Given
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.toResponseDTO(restaurant)).thenReturn(restaurantResponseDTO);

        // When
        RestaurantResponseDTO result = restaurantService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(restaurantResponseDTO.getId(), result.getId());
        verify(restaurantRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        RestaurantResponseDTO result = restaurantService.findById(1L);

        // Then
        assertNull(result);
        verify(restaurantRepository).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Given
        when(restaurantRepository.existsById(1L)).thenReturn(true);

        // When
        boolean result = restaurantService.deleteById(1L);

        // Then
        assertTrue(result);
        verify(restaurantRepository).existsById(1L);
        verify(restaurantRepository).deleteById(1L);
    }
}
