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
        Long visitor1Id = visitorService.save(new VisitorRequestDTO("Иван Петров", 25, "М")).getId();
        Long visitor2Id = visitorService.save(new VisitorRequestDTO("Мария Сидорова", 30, "Ж")).getId();
        Long visitor3Id = visitorService.save(new VisitorRequestDTO(null, 22, "М")).getId(); // анонимный отзыв
        
        // Добавляем рестораны
        Long restaurant1Id = restaurantService.save(new RestaurantRequestDTO("Pasta Paradise", 
                "Лучшая итальянская кухня", CuisineType.ITALIAN, BigDecimal.valueOf(1500))).getId();
        Long restaurant2Id = restaurantService.save(new RestaurantRequestDTO("Dragon Palace", 
                "Аутентичная китайская кухня", CuisineType.CHINESE, BigDecimal.valueOf(1200))).getId();
        Long restaurant3Id = restaurantService.save(new RestaurantRequestDTO("Café Europe", 
                "", CuisineType.EUROPEAN, BigDecimal.valueOf(2000))).getId();
        
        // Добавляем отзывы
        reviewService.save(new ReviewRequestDTO(visitor1Id, restaurant1Id, 5, "Отличная паста!"));
        reviewService.save(new ReviewRequestDTO(visitor2Id, restaurant1Id, 4, "Хорошее место"));
        reviewService.save(new ReviewRequestDTO(visitor3Id, restaurant2Id, 3, ""));
        reviewService.save(new ReviewRequestDTO(visitor1Id, restaurant3Id, 4, "Неплохо"));
        
        System.out.println("=== Тестирование системы оценки ресторанов с JPA ===");
        
        // Тестируем посетителей
        System.out.println("\n--- Посетители ---");
        visitorService.findAll().forEach(System.out::println);
        
        // Тестируем рестораны
        System.out.println("\n--- Рестораны ---");
        restaurantService.findAll().forEach(System.out::println);
        
        // Тестируем отзывы
        System.out.println("\n--- Отзывы ---");
        reviewService.findAll().forEach(System.out::println);
        
        System.out.println("\n=== API доступно по адресу: http://localhost:8080/swagger-ui/index.html ===");
        System.out.println("=== H2 Console доступна по адресу: http://localhost:8080/h2-console ===");
    }
}
