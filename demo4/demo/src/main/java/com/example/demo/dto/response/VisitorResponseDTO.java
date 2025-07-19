package com.example.demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class VisitorResponseDTO {
    
    @Schema(description = "ID посетителя", example = "1")
    private Long id;
    
    @Schema(description = "Имя посетителя", example = "Иван Петров")
    private String name;
    
    @Schema(description = "Возраст посетителя", example = "25")
    private int age;
    
    @Schema(description = "Пол посетителя", example = "М")
    private String gender;
    
    // Конструкторы
    public VisitorResponseDTO() {}
    
    public VisitorResponseDTO(Long id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    
    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
