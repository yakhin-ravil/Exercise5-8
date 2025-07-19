package com.example.demo.repository;

import com.example.demo.entity.Visitor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitorRepository {
    private final List<Visitor> visitors = new ArrayList<>();
    
    public Visitor save(Visitor visitor) {
        visitors.add(visitor);
        return visitor;
    }
    
    public boolean remove(Visitor visitor) {
        return visitors.remove(visitor);
    }
    
    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }
}
