package com.example.demo.service;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.response.VisitorResponseDTO;
import com.example.demo.entity.Visitor;
import com.example.demo.mapper.VisitorMapper;
import com.example.demo.repository.VisitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitorServiceTest {

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private VisitorMapper visitorMapper;

    @InjectMocks
    private VisitorService visitorService;

    private Visitor visitor;
    private VisitorRequestDTO visitorRequestDTO;
    private VisitorResponseDTO visitorResponseDTO;

    @BeforeEach
    void setUp() {
        visitor = new Visitor("Иван Петров", 25, "М");
        visitor.setId(1L);
        
        visitorRequestDTO = new VisitorRequestDTO("Иван Петров", 25, "М");
        visitorResponseDTO = new VisitorResponseDTO(1L, "Иван Петров", 25, "М");
    }

    @Test
    void testSave() {
        // Given
        when(visitorMapper.toEntity(visitorRequestDTO)).thenReturn(visitor);
        when(visitorRepository.save(any(Visitor.class))).thenReturn(visitor);
        when(visitorMapper.toResponseDTO(visitor)).thenReturn(visitorResponseDTO);

        // When
        VisitorResponseDTO result = visitorService.save(visitorRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(visitorResponseDTO.getId(), result.getId());
        assertEquals(visitorResponseDTO.getName(), result.getName());
        verify(visitorRepository).save(any(Visitor.class));
        verify(visitorMapper).toEntity(visitorRequestDTO);
        verify(visitorMapper).toResponseDTO(visitor);
    }

    @Test
    void testFindAll() {
        // Given
        List<Visitor> visitors = Arrays.asList(visitor);
        List<VisitorResponseDTO> expectedDTOs = Arrays.asList(visitorResponseDTO);
        
        when(visitorRepository.findAll()).thenReturn(visitors);
        when(visitorMapper.toResponseDTO(visitor)).thenReturn(visitorResponseDTO);

        // When
        List<VisitorResponseDTO> result = visitorService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedDTOs.get(0).getName(), result.get(0).getName());
        verify(visitorRepository).findAll();
    }

    @Test
    void testFindById() {
        // Given
        when(visitorRepository.findById(1L)).thenReturn(Optional.of(visitor));
        when(visitorMapper.toResponseDTO(visitor)).thenReturn(visitorResponseDTO);

        // When
        VisitorResponseDTO result = visitorService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(visitorResponseDTO.getId(), result.getId());
        verify(visitorRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        when(visitorRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        VisitorResponseDTO result = visitorService.findById(1L);

        // Then
        assertNull(result);
        verify(visitorRepository).findById(1L);
    }

    @Test
    void testUpdate() {
        // Given
        when(visitorRepository.findById(1L)).thenReturn(Optional.of(visitor));
        when(visitorRepository.save(any(Visitor.class))).thenReturn(visitor);
        when(visitorMapper.toResponseDTO(visitor)).thenReturn(visitorResponseDTO);

        // When
        VisitorResponseDTO result = visitorService.update(1L, visitorRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(visitorResponseDTO.getId(), result.getId());
        verify(visitorRepository).findById(1L);
        verify(visitorRepository).save(visitor);
        verify(visitorMapper).updateEntity(visitor, visitorRequestDTO);
    }

    @Test
    void testDeleteById() {
        // Given
        when(visitorRepository.existsById(1L)).thenReturn(true);

        // When
        boolean result = visitorService.deleteById(1L);

        // Then
        assertTrue(result);
        verify(visitorRepository).existsById(1L);
        verify(visitorRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        // Given
        when(visitorRepository.existsById(1L)).thenReturn(false);

        // When
        boolean result = visitorService.deleteById(1L);

        // Then
        assertFalse(result);
        verify(visitorRepository).existsById(1L);
        verify(visitorRepository, never()).deleteById(1L);
    }
}
