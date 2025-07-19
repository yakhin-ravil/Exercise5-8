package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name; // может быть null (анонимный отзыв)
    
    @Column(name = "age", nullable = false)
    private int age;
    
    @Column(name = "gender", nullable = false)
    private String gender;
    
    // Конструкторы
    public Visitor() {}
    
    public Visitor(String name, int age, String gender) {
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
        return String.format("Visitor{id=%d, name='%s', age=%d, gender='%s'}", 
                id, name, age, gender);
    }
}
