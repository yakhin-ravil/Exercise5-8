package com.example.demo.controller;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.response.VisitorResponseDTO;
import com.example.demo.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@Tag(name = "Посетители", description = "API для управления посетителями")
public class VisitorController {
    
    @Autowired
    private VisitorService visitorService;
    
    @GetMapping
    @Operation(summary = "Получить всех посетителей")
    public ResponseEntity<List<VisitorResponseDTO>> getAllVisitors() {
        List<VisitorResponseDTO> visitors = visitorService.findAll();
        return ResponseEntity.ok(visitors);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Получить посетителя по ID")
    public ResponseEntity<VisitorResponseDTO> getVisitorById(@PathVariable Long id) {
        VisitorResponseDTO visitor = visitorService.findById(id);
        if (visitor != null) {
            return ResponseEntity.ok(visitor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Operation(summary = "Создать нового посетителя")
    public ResponseEntity<VisitorResponseDTO> createVisitor(@RequestBody VisitorRequestDTO dto) {
        VisitorResponseDTO createdVisitor = visitorService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVisitor);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Обновить посетителя")
    public ResponseEntity<VisitorResponseDTO> updateVisitor(@PathVariable Long id, 
                                                           @RequestBody VisitorRequestDTO dto) {
        VisitorResponseDTO updatedVisitor = visitorService.update(id, dto);
        if (updatedVisitor != null) {
            return ResponseEntity.ok(updatedVisitor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить посетителя")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        boolean deleted = visitorService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
