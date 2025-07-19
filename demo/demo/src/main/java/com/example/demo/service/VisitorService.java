package com.example.demo.service;

import com.example.demo.entity.Visitor;
import com.example.demo.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitorService {
    
    @Autowired
    private VisitorRepository visitorRepository;
    
    public Visitor save(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
    
    public boolean remove(Visitor visitor) {
        return visitorRepository.remove(visitor);
    }
    
    public List<Visitor> findAll() {
        return visitorRepository.findAll();
    }
}
