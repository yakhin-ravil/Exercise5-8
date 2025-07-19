package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class VisitorRequestDTO {
    
    @Schema(description = "Имя посетителя", example = "Иван Петров")
    private String name;
    
    @Schema(description = "Возраст посетителя", example = "25", required = true)
    private int age;
    
    @Schema(description = "Пол посетителя", example = "М", required = true)
    private String gender;
    
    // Конструкторы
    public VisitorRequestDTO() {}
    
    public VisitorRequestDTO(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    
    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
