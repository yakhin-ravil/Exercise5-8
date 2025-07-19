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
    
    public VisitorResponseDTO save(VisitorRequestDTO dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        Visitor savedVisitor = visitorRepository.save(visitor);
        return visitorMapper.toResponseDTO(savedVisitor);
    }
    
    public List<VisitorResponseDTO> findAll() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public VisitorResponseDTO findById(Long id) {
        return visitorRepository.findById(id)
                .map(visitorMapper::toResponseDTO)
                .orElse(null);
    }
    
    public VisitorResponseDTO update(Long id, VisitorRequestDTO dto) {
        return visitorRepository.findById(id)
                .map(visitor -> {
                    visitorMapper.updateEntity(visitor, dto);
                    Visitor updatedVisitor = visitorRepository.save(visitor);
                    return visitorMapper.toResponseDTO(updatedVisitor);
                })
                .orElse(null);
    }
    
    public boolean deleteById(Long id) {
        if (visitorRepository.existsById(id)) {
            visitorRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Вспомогательный метод для получения сущности
    public Visitor getEntityById(Long id) {
        return visitorRepository.findById(id).orElse(null);
    }
}
