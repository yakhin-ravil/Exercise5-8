package com.example.demo;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.request.RestaurantRequestDTO;
import com.example.demo.dto.request.ReviewRequestDTO;
import com.example.demo.enums.CuisineType;
import com.example.demo.service.VisitorService;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TestRunner implements CommandLineRunner {
    
    @Autowired
    private VisitorService visitorService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private ReviewService reviewService;
    
    @Override
    public void run(String... args) throws Exception {
        // Добавляем посетителей
        visitorService.save(new VisitorRequestDTO("Иван Петров", 25, "М"));
        visitorService.save(new VisitorRequestDTO("Мария Сидорова", 30, "Ж"));
        visitorService.save(new VisitorRequestDTO(null, 22, "М")); // анонимный отзыв
        
        // Добавляем рестораны
        restaurantService.save(new RestaurantRequestDTO("Pasta Paradise", 
                "Лучшая итальянская кухня", CuisineType.ITALIAN, BigDecimal.valueOf(1500)));
        restaurantService.save(new RestaurantRequestDTO("Dragon Palace", 
                "Аутентичная китайская кухня", CuisineType.CHINESE, BigDecimal.valueOf(1200)));
        restaurantService.save(new RestaurantRequestDTO("Café Europe", 
                "", CuisineType.EUROPEAN, BigDecimal.valueOf(2000)));
        
        // Добавляем отзывы
        reviewService.save(new ReviewRequestDTO(1L, 1L, 5, "Отличная паста!"));
        reviewService.save(new ReviewRequestDTO(2L, 1L, 4, "Хорошее место"));
        reviewService.save(new ReviewRequestDTO(3L, 2L, 3, ""));
        reviewService.save(new ReviewRequestDTO(1L, 3L, 4, "Неплохо"));
        
        System.out.println("=== API доступно по адресу: http://localhost:8080/swagger-ui/index.html ===");
    }
}
