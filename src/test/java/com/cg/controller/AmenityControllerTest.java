package com.cg.controller;

import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;
import com.cg.service.AmenityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AmenityController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = AmenityController.class)
@TestPropertySource(properties = {
        "spring.main.allow-bean-definition-overriding=true"
})

class AmenityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmenityService service;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔹 1. CREATE
    @Test
    void testCreate() throws Exception {
        AmenityRequestDTO req = new AmenityRequestDTO();
        req.setAmenityId(1L);
        req.setName("WiFi");
        req.setDescription("Internet");

        AmenityResponseDTO res = new AmenityResponseDTO();
        res.setAmenityId(1L);
        res.setName("WiFi");

        Mockito.when(service.createAmenity(Mockito.any())).thenReturn(res);

        mockMvc.perform(post("/api/amenities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("WiFi"));
    }

    // 🔹 2. GET ALL
    @Test
    void testGetAll() throws Exception {
        AmenityResponseDTO res = new AmenityResponseDTO();
        res.setAmenityId(1L);

        Mockito.when(service.getAllAmenities()).thenReturn(List.of(res));

        mockMvc.perform(get("/api/amenities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amenityId").value(1));
    }

    // 🔹 3. GET BY ID
    @Test
    void testGetById() throws Exception {
        AmenityResponseDTO res = new AmenityResponseDTO();
        res.setAmenityId(1L);

        Mockito.when(service.getAmenityById(1L)).thenReturn(res);

        mockMvc.perform(get("/api/amenities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amenityId").value(1));
    }

    // 🔹 4. UPDATE
    @Test
    void testUpdate() throws Exception {
        AmenityRequestDTO req = new AmenityRequestDTO();
        req.setName("Updated");

        AmenityResponseDTO res = new AmenityResponseDTO();
        res.setName("Updated");

        Mockito.when(service.updateAmenity(Mockito.eq(1L), Mockito.any()))
                .thenReturn(res);

        mockMvc.perform(put("/api/amenities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    // 🔹 5. DELETE
    @Test
    void testDelete() throws Exception {
        Mockito.doNothing().when(service).deleteAmenity(1L);

        mockMvc.perform(delete("/api/amenities/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        Mockito.verify(service).deleteAmenity(1L);
    }

    // 🔹 6. SEARCH BY NAME
    @Test
    void testSearchByName() throws Exception {
        AmenityResponseDTO res = new AmenityResponseDTO();
        res.setName("Pool");

        Mockito.when(service.getAmenityByName("Pool")).thenReturn(res);

        mockMvc.perform(get("/api/amenities/search")
                        .param("name", "Pool"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pool"));
    }

    // 🔹 7. EXISTS
    @Test
    void testExists() throws Exception {
        Mockito.when(service.existsById(1L)).thenReturn(true);

        mockMvc.perform(get("/api/amenities/exists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    // 🔹 8. COUNT
    @Test
    void testCount() throws Exception {
        Mockito.when(service.countAmenities()).thenReturn(5L);

        mockMvc.perform(get("/api/amenities/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    // 🔹 9. ASSIGN TO ROOM
    @Test
    void testAssignToRoom() throws Exception {
        Mockito.doNothing().when(service).assignAmenityToRoom(1L, 2L);

        mockMvc.perform(post("/api/amenities/assign/room")
                        .param("roomId", "1")
                        .param("amenityId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Assigned to room"));

        Mockito.verify(service).assignAmenityToRoom(1L, 2L);
    }

    // 🔹 10. REMOVE FROM ROOM
    @Test
    void testRemoveFromRoom() throws Exception {
        Mockito.doNothing().when(service).removeAmenityFromRoom(1L, 2L);

        mockMvc.perform(delete("/api/amenities/remove/room")
                        .param("roomId", "1")
                        .param("amenityId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Removed from room"));

        Mockito.verify(service).removeAmenityFromRoom(1L, 2L);
    }
}