package com.api.parkingcontrol;

import com.api.parkingcontrol.repository.ParkingSpotRepository;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.api.parkingcontrol.entity.ParkingSpotEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotServiceTest {
    @Mock
    private ParkingSpotRepository repository;

    @InjectMocks
    private ParkingSpotService service;

    @Test
    void deveSalvarQuandoPlacaNaoExiste() {
        ParkingSpotEntity spot = new ParkingSpotEntity();
        spot.setLicensePlateCar("ABC1234");

        when(repository.existsByLicensePlateCar("ABC1234"))
                .thenReturn(false);

        when(repository.save(spot)).thenReturn(spot);

        ParkingSpotEntity result = service.save(spot);

        assertNotNull(result);
        verify(repository).save(spot);
    }

    @Test
    void deveLancarErroQuandoPlacaJaExiste() {
        ParkingSpotEntity spot = new ParkingSpotEntity();
        spot.setLicensePlateCar("ABC1234");

        when(repository.existsByLicensePlateCar("ABC1234"))
                .thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            service.save(spot);
        });

        verify(repository, never()).save(any());
    }



}
