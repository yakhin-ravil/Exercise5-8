package com.example.demo.entity;

public class Visitor {
    private Long id;
    private String name; // может быть null (анонимный отзыв)
    private int age;
    private String gender;
    
    // Конструкторы
    public Visitor() {}
    
    public Visitor(Long id, String name, int age, String gender) {
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
    
    @Override
    public String toString() {
        return "Visitor{id=" + id + ", name='" + name + "', age=" + age + ", gender='" + gender + "'}";
    }
}
