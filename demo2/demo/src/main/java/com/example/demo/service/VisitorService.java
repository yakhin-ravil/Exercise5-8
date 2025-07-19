package com.example.demo.service;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.response.VisitorResponseDTO;
import com.example.demo.entity.Visitor;
import com.example.demo.mapper.VisitorMapper;
import com.example.demo.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    
    @Autowired
    private VisitorRepository visitorRepository;
    
    @Autowired
    private VisitorMapper visitorMapper;
    
    private Long nextId = 1L;
    
    public VisitorResponseDTO save(VisitorRequestDTO dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        visitor.setId(nextId++);
        Visitor savedVisitor = visitorRepository.save(visitor);
        return visitorMapper.toResponseDTO(savedVisitor);
    }
    
    public List<VisitorResponseDTO> findAll() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public VisitorResponseDTO findById(Long id) {
        List<Visitor> visitors = visitorRepository.findAll();
        for (Visitor visitor : visitors) {
            if (visitor.getId().equals(id)) {
                return visitorMapper.toResponseDTO(visitor);
            }
        }
        return null;
    }
    
    public VisitorResponseDTO update(Long id, VisitorRequestDTO dto) {
        List<Visitor> visitors = visitorRepository.findAll();
        for (Visitor visitor : visitors) {
            if (visitor.getId().equals(id)) {
                visitorMapper.updateEntity(visitor, dto);
                return visitorMapper.toResponseDTO(visitor);
            }
        }
        return null;
    }
    
    public boolean deleteById(Long id) {
        List<Visitor> visitors = visitorRepository.findAll();
        for (Visitor visitor : visitors) {
            if (visitor.getId().equals(id)) {
                return visitorRepository.remove(visitor);
            }
        }
        return false;
    }
}
