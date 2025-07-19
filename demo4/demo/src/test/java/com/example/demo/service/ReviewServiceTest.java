package com.example.demo.service;

import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.dto.response.ReviewResponseDTO;
import com.example.demo.entity.Review;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Visitor;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private VisitorService visitorService;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private ReviewRequestDTO reviewRequestDTO;
    private ReviewResponseDTO reviewResponseDTO;
    private Visitor visitor;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        visitor = new Visitor("Иван Петров", 25, "М");
        visitor.setId(1L);

        restaurant = new Restaurant();
        restaurant.setId(1L);

        review = new Review(visitor, restaurant, 5, "Отличная паста!");
        review.setId(1L);

        reviewRequestDTO = new ReviewRequestDTO(1L, 1L, 5, "Отличная паста!");
        reviewResponseDTO = new ReviewResponseDTO(1L, 1L, 5, "Отличная паста!");
    }

    @Test
    void testSave() {
        // Given
        when(visitorService.getEntityById(1L)).thenReturn(visitor);
        when(restaurantService.getEntityById(1L)).thenReturn(restaurant);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        when(reviewMapper.toResponseDTO(review)).thenReturn(reviewResponseDTO);
        when(reviewRepository.findAverageRatingByRestaurantId(1L)).thenReturn(5.0);

        // When
        ReviewResponseDTO result = reviewService.save(reviewRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(reviewResponseDTO.getRestaurantId(), result.getRestaurantId());
        assertEquals(reviewResponseDTO.getRating(), result.getRating());
        verify(reviewRepository).save(any(Review.class));
        verify(reviewMapper).toResponseDTO(review);
    }

    @Test
    void testSaveWithNullVisitor() {
        // Given
        when(visitorService.getEntityById(1L)).thenReturn(null);
        when(restaurantService.getEntityById(1L)).thenReturn(restaurant);

        // When
        ReviewResponseDTO result = reviewService.save(reviewRequestDTO);

        // Then
        assertNull(result);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testSaveWithNullRestaurant() {
        // Given
        when(visitorService.getEntityById(1L)).thenReturn(visitor);
        when(restaurantService.getEntityById(1L)).thenReturn(null);

        // When
        ReviewResponseDTO result = reviewService.save(reviewRequestDTO);

        // Then
        assertNull(result);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testFindAll() {
        // Given
        List<Review> reviews = Arrays.asList(review);
        
        when(reviewRepository.findAll()).thenReturn(reviews);
        when(reviewMapper.toResponseDTO(review)).thenReturn(reviewResponseDTO);

        // When
        List<ReviewResponseDTO> result = reviewService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reviewResponseDTO.getRating(), result.get(0).getRating());
        verify(reviewRepository).findAll();
    }

    @Test
    void testDeleteById() {
        // Given
        when(reviewRepository.existsById(1L)).thenReturn(true);
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // When
        boolean result = reviewService.deleteById(1L);

        // Then
        assertTrue(result);
        verify(reviewRepository).existsById(1L);
        verify(reviewRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        // Given
        when(reviewRepository.existsById(1L)).thenReturn(false);

        // When
        boolean result = reviewService.deleteById(1L);

        // Then
        assertFalse(result);
        verify(reviewRepository).existsById(1L);
        verify(reviewRepository, never()).deleteById(1L);
    }
}
