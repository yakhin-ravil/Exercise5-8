package com.example.demo.controller;

import com.example.demo.dto.request.RestaurantRequestDTO;
import com.example.demo.dto.response.RestaurantResponseDTO;
import com.example.demo.enums.CuisineType;
import com.example.demo.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    private RestaurantRequestDTO restaurantRequestDTO;
    private RestaurantResponseDTO restaurantResponseDTO;

    @BeforeEach
    void setUp() {
        restaurantRequestDTO = new RestaurantRequestDTO("Pasta Paradise", "Лучшая итальянская кухня",
                CuisineType.ITALIAN, BigDecimal.valueOf(1500));
        
        restaurantResponseDTO = new RestaurantResponseDTO(1L, "Pasta Paradise", "Лучшая итальянская кухня",
                CuisineType.ITALIAN, BigDecimal.valueOf(1500), BigDecimal.valueOf(4.5));
    }

    @Test
    void testGetAllRestaurants() throws Exception {
        // Given
        List<RestaurantResponseDTO> restaurants = Arrays.asList(restaurantResponseDTO);
        when(restaurantService.findAll()).thenReturn(restaurants);

        // When & Then
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Pasta Paradise"))
                .andExpect(jsonPath("$[0].cuisineType").value("ITALIAN"));

        verify(restaurantService).findAll();
    }

    @Test
    void testGetRestaurantById() throws Exception {
        // Given
        when(restaurantService.findById(1L)).thenReturn(restaurantResponseDTO);

        // When & Then
        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pasta Paradise"));

        verify(restaurantService).findById(1L);
    }

    @Test
    void testCreateRestaurant() throws Exception {
        // Given
        when(restaurantService.save(any(RestaurantRequestDTO.class))).thenReturn(restaurantResponseDTO);

        // When & Then
        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pasta Paradise"));

        verify(restaurantService).save(any(RestaurantRequestDTO.class));
    }

    @Test
    void testUpdateRestaurant() throws Exception {
        // Given
        when(restaurantService.update(eq(1L), any(RestaurantRequestDTO.class))).thenReturn(restaurantResponseDTO);

        // When & Then
        mockMvc.perform(put("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pasta Paradise"));

        verify(restaurantService).update(eq(1L), any(RestaurantRequestDTO.class));
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        // Given
        when(restaurantService.deleteById(1L)).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isNoContent());

        verify(restaurantService).deleteById(1L);
    }
}
