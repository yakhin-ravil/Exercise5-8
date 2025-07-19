package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // Поиск отзывов по ресторану через объект Restaurant
    List<Review> findByRestaurant_Id(Long restaurantId);
    
    // Поиск отзывов по посетителю
    List<Review> findByVisitor_Id(Long visitorId);
    
    // Поиск отзывов по рейтингу
    List<Review> findByRating(int rating);
    
    // Средний рейтинг ресторана через @Query
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.restaurant.id = :restaurantId")
    Double findAverageRatingByRestaurantId(@Param("restaurantId") Long restaurantId);
}
