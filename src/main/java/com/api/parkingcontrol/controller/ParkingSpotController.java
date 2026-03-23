package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.entity.ParkingSpotEntity;
import com.api.parkingcontrol.services.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;


@Tag(name = "Parking Spot", description = "API para gerenciamento de vagas de estacionamento")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @Operation(summary = "Criar uma nova vaga de estacionamento")
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {

        var parkingSpotModel = new ParkingSpotEntity();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);

        parkingSpotModel.setId(UUID.randomUUID());
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingSpotService.save(parkingSpotModel));
    }

    @Operation(summary = "Listar todas as vagas com paginação")
    @GetMapping
    public ResponseEntity<Page<ParkingSpotEntity>> getAllParkingSpot(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body((parkingSpotService.findAll(pageable)));
    }

    @Operation(summary = "Buscar vaga por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable UUID id) {

        var parkingSpotOptional = parkingSpotService.findById(id);

        if (parkingSpotOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parking Spot not found.");
        }

        return ResponseEntity.ok(parkingSpotOptional.get());
    }

    @Operation(summary = "Deletar vaga por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable UUID id) {

        var parkingSpotOptional = parkingSpotService.findById(id);

        if (parkingSpotOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parking Spot not found.");
        }

        parkingSpotService.delete(parkingSpotOptional.get());

        return ResponseEntity.ok("Parking Spot deleted successfully");
    }

    @Operation(summary = "Atualizar vaga por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParking(@PathVariable UUID id,
                                                @RequestBody @Valid ParkingSpotDto parkingSpotDto) {

        var parkingSpotOptional = parkingSpotService.findById(id);

        if (parkingSpotOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Parking Spot not found.");
        }

        var parkingSpotModel = parkingSpotOptional.get();

        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);

        return ResponseEntity.ok(parkingSpotService.save(parkingSpotModel));
    }

}


