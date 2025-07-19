package com.example.demo;

import com.example.demo.entity.Visitor;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Review;
import com.example.demo.enums.CuisineType;
import com.example.demo.service.VisitorService;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class TestRunner implements CommandLineRunner {
    
    @Autowired
    private VisitorService visitorService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private ReviewService reviewService;
    
    @PostConstruct
    public void initData() {
        // Добавляем посетителей
        visitorService.save(new Visitor(1L, "Иван Петров", 25, "М"));
        visitorService.save(new Visitor(2L, "Мария Сидорова", 30, "Ж"));
        visitorService.save(new Visitor(3L, null, 22, "М")); // анонимный отзыв
        
        // Добавляем рестораны
        restaurantService.save(new Restaurant(1L, "Pasta Paradise", "Лучшая итальянская кухня", 
                CuisineType.ITALIAN, BigDecimal.valueOf(1500), BigDecimal.ZERO));
        restaurantService.save(new Restaurant(2L, "Dragon Palace", "Аутентичная китайская кухня", 
                CuisineType.CHINESE, BigDecimal.valueOf(1200), BigDecimal.ZERO));
        restaurantService.save(new Restaurant(3L, "Café Europe", "", 
                CuisineType.EUROPEAN, BigDecimal.valueOf(2000), BigDecimal.ZERO));
        
        // Добавляем оценки
        reviewService.save(new Review(1L, 1L, 5, "Отличная паста!"));
        reviewService.save(new Review(2L, 1L, 4, "Хорошее место"));
        reviewService.save(new Review(3L, 2L, 3, ""));
        reviewService.save(new Review(1L, 3L, 4, "Неплохо"));
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Тестирование системы оценки ресторанов ===");
        
        // Тестируем посетителей
        System.out.println("\n--- Посетители ---");
        visitorService.findAll().forEach(System.out::println);
        
        // Тестируем рестораны
        System.out.println("\n--- Рестораны ---");
        restaurantService.findAll().forEach(System.out::println);
        
        // Тестируем отзывы
        System.out.println("\n--- Отзывы ---");
        reviewService.findAll().forEach(System.out::println);
        
        System.out.println("\n=== Тестирование завершено ===");
    }
}
