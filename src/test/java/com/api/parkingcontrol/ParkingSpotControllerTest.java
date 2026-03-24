package com.api.parkingcontrol;

import com.api.parkingcontrol.controller.ParkingSpotController;
import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.entity.ParkingSpotEntity;
import com.api.parkingcontrol.services.ParkingSpotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParkingSpotController.class)
public class ParkingSpotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingSpotService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarParkingSpot() throws Exception {

        ParkingSpotDto dto = criarDtoValido();

        ParkingSpotEntity entity = new ParkingSpotEntity();
        entity.setLicensePlateCar("ABC1234");

        when(service.save(any(ParkingSpotEntity.class))).thenReturn(entity);

        mockMvc.perform(post("/parking-spot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.licensePlateCar").value("ABC1234"));

        verify(service).save(any(ParkingSpotEntity.class));
    }

    @Test
    void deveListarParkingSpots() throws Exception {

        ParkingSpotEntity entity = new ParkingSpotEntity();
        entity.setLicensePlateCar("ABC1234");

        when(service.findAll(any()))
                .thenReturn(new PageImpl<>(List.of(entity)));

        mockMvc.perform(get("/parking-spot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].licensePlateCar").value("ABC1234"));

        verify(service).findAll(any());
    }

    @Test
    void deveRetornarErroQuandoDadosInvalidos() throws Exception {

        mockMvc.perform(post("/parking-spot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ParkingSpotDto())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarParkingSpotPorId() throws Exception {

        UUID id = UUID.randomUUID();

        ParkingSpotEntity entity = new ParkingSpotEntity();
        entity.setId(id);
        entity.setLicensePlateCar("ABC1234");

        when(service.findById(id)).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/parking-spot/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlateCar").value("ABC1234"))
                .andExpect(jsonPath("$.id").value(id.toString()));

        verify(service).findById(id);
    }

    @Test
    void deveRetornar404QuandoNaoEncontrar() throws Exception {

        UUID id = UUID.randomUUID();

        when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/parking-spot/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarParkingSpot() throws Exception {

        UUID id = UUID.randomUUID();

        ParkingSpotEntity entity = new ParkingSpotEntity();
        entity.setId(id);

        when(service.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(service).delete(any(ParkingSpotEntity.class));

        mockMvc.perform(delete("/parking-spot/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Parking Spot deleted successfully"));

        verify(service).delete(any(ParkingSpotEntity.class));
    }

    @Test
    void deveRetornar404AoDeletar() throws Exception {

        UUID id = UUID.randomUUID();

        when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/parking-spot/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarParkingSpot() throws Exception {

        UUID id = UUID.randomUUID();

        ParkingSpotDto dto = criarDtoValido();

        ParkingSpotEntity entity = new ParkingSpotEntity();
        entity.setId(id);
        entity.setLicensePlateCar("ABC1234");

        when(service.findById(id)).thenReturn(Optional.of(entity));
        when(service.save(any(ParkingSpotEntity.class))).thenReturn(entity);

        mockMvc.perform(put("/parking-spot/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlateCar").value("ABC1234"));

        verify(service).save(any(ParkingSpotEntity.class));
    }

    @Test
    void deveRetornar404AoAtualizar() throws Exception {

        UUID id = UUID.randomUUID();

        when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(put("/parking-spot/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criarDtoValido())))
                .andExpect(status().isNotFound());
    }

    private ParkingSpotDto criarDtoValido() {
        ParkingSpotDto dto = new ParkingSpotDto();
        dto.setLicensePlateCar("ABC1234");
        dto.setParkingSpotNumber("001");
        dto.setApartment("101");
        dto.setBlock("A");
        dto.setBrandCar("Toyota");
        dto.setModelCar("Corolla");
        dto.setColorCar("Preto");
        dto.setResponsableName("Victor");
        return dto;
    }
}