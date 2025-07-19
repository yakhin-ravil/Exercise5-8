package com.example.demo.controller;

import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewRequestDTO reviewRequestDTO;
    private ReviewResponseDTO reviewResponseDTO;

    @BeforeEach
    void setUp() {
        reviewRequestDTO = new ReviewRequestDTO(1L, 1L, 5, "Отличная паста!");
        reviewResponseDTO = new ReviewResponseDTO(1L, 1L, 5, "Отличная паста!");
    }

    @Test
    void testGetAllReviews() throws Exception {
        // Given
        List<ReviewResponseDTO> reviews = Arrays.asList(reviewResponseDTO);
        when(reviewService.findAll()).thenReturn(reviews);

        // When & Then
        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].visitorId").value(1L))
                .andExpect(jsonPath("$[0].restaurantId").value(1L))
                .andExpect(jsonPath("$[0].rating").value(5));

        verify(reviewService).findAll();
    }

    @Test
    void testCreateReview() throws Exception {
        // Given
        when(reviewService.save(any(ReviewRequestDTO.class))).thenReturn(reviewResponseDTO);

        // When & Then
        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.visitorId").value(1L))
                .andExpect(jsonPath("$.restaurantId").value(1L))
                .andExpect(jsonPath("$.rating").value(5));

        verify(reviewService).save(any(ReviewRequestDTO.class));
    }

    // @Test
    // void testDeleteReview() throws Exception {
    //     // Given
    //     when(reviewService.deleteById(1L)).thenReturn(true);

    //     // When & Then
    //     mockMvc.perform(delete("/api/reviews/1"))
    //             .andExpect(status().isNoContent());

    //     verify(reviewService).deleteById(1L);
    // }

    // @Test
    // void testDeleteReviewNotFound() throws Exception {
    //     // Given
    //     when(reviewService.deleteById(1L)).thenReturn(false);

    //     // When & Then
    //     mockMvc.perform(delete("/api/reviews/1"))
    //             .andExpect(status().isNotFound());

    //     verify(reviewService).deleteById(1L);
    // }

    @Test
    void testDeleteReview() throws Exception {
        when(reviewService.deleteById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isNoContent());

        verify(reviewService).deleteById(1L);
    }

    @Test
    void testDeleteReviewNotFound() throws Exception {
        when(reviewService.deleteById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isNotFound());

        verify(reviewService).deleteById(1L);
    }

}
