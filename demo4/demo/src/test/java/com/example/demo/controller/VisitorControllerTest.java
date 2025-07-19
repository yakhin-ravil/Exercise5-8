package com.example.demo.controller;

import com.example.demo.dto.request.VisitorRequestDTO;
import com.example.demo.dto.response.VisitorResponseDTO;
import com.example.demo.service.VisitorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VisitorController.class)
class VisitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    @Autowired
    private ObjectMapper objectMapper;

    private VisitorRequestDTO visitorRequestDTO;
    private VisitorResponseDTO visitorResponseDTO;

    @BeforeEach
    void setUp() {
        visitorRequestDTO = new VisitorRequestDTO("Иван Петров", 25, "М");
        visitorResponseDTO = new VisitorResponseDTO(1L, "Иван Петров", 25, "М");
    }

    @Test
    void testGetAllVisitors() throws Exception {
        // Given
        List<VisitorResponseDTO> visitors = Arrays.asList(visitorResponseDTO);
        when(visitorService.findAll()).thenReturn(visitors);

        // When & Then
        mockMvc.perform(get("/api/visitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Иван Петров"))
                .andExpect(jsonPath("$[0].age").value(25));

        verify(visitorService).findAll();
    }

    @Test
    void testGetVisitorById() throws Exception {
        // Given
        when(visitorService.findById(1L)).thenReturn(visitorResponseDTO);

        // When & Then
        mockMvc.perform(get("/api/visitors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Иван Петров"))
                .andExpect(jsonPath("$.age").value(25));

        verify(visitorService).findById(1L);
    }

    @Test
    void testGetVisitorByIdNotFound() throws Exception {
        // Given
        when(visitorService.findById(1L)).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/api/visitors/1"))
                .andExpect(status().isNotFound());

        verify(visitorService).findById(1L);
    }

    @Test
    void testCreateVisitor() throws Exception {
        // Given
        when(visitorService.save(any(VisitorRequestDTO.class))).thenReturn(visitorResponseDTO);

        // When & Then
        mockMvc.perform(post("/api/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitorRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Иван Петров"));

        verify(visitorService).save(any(VisitorRequestDTO.class));
    }

    @Test
    void testUpdateVisitor() throws Exception {
        // Given
        when(visitorService.update(eq(1L), any(VisitorRequestDTO.class))).thenReturn(visitorResponseDTO);

        // When & Then
        mockMvc.perform(put("/api/visitors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitorRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Иван Петров"));

        verify(visitorService).update(eq(1L), any(VisitorRequestDTO.class));
    }

    @Test
    void testUpdateVisitorNotFound() throws Exception {
        // Given
        when(visitorService.update(eq(1L), any(VisitorRequestDTO.class))).thenReturn(null);

        // When & Then
        mockMvc.perform(put("/api/visitors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitorRequestDTO)))
                .andExpect(status().isNotFound());

        verify(visitorService).update(eq(1L), any(VisitorRequestDTO.class));
    }

    @Test
    void testDeleteVisitor() throws Exception {
        // Given
        when(visitorService.deleteById(1L)).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/api/visitors/1"))
                .andExpect(status().isNoContent());

        verify(visitorService).deleteById(1L);
    }

    @Test
    void testDeleteVisitorNotFound() throws Exception {
        // Given
        when(visitorService.deleteById(1L)).thenReturn(false);

        // When & Then
        mockMvc.perform(delete("/api/visitors/1"))
                .andExpect(status().isNotFound());

        verify(visitorService).deleteById(1L);
    }
}
