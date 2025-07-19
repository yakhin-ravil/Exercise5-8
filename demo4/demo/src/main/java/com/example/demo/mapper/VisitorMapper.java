package com.example.demo.mapper;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.response.VisitorResponseDTO;
import com.example.demo.entity.Visitor;
import org.springframework.stereotype.Component;

@Component
public class VisitorMapper {
    
    public Visitor toEntity(VisitorRequestDTO dto) {
        if (dto == null) return null;
        
        Visitor visitor = new Visitor();
        visitor.setName(dto.getName());
        visitor.setAge(dto.getAge());
        visitor.setGender(dto.getGender());
        return visitor;
    }
    
    public VisitorResponseDTO toResponseDTO(Visitor entity) {
        if (entity == null) return null;
        
        return new VisitorResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getAge(),
            entity.getGender()
        );
    }
    
    public void updateEntity(Visitor entity, VisitorRequestDTO dto) {
        if (entity == null || dto == null) return;
        
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
    }
}
