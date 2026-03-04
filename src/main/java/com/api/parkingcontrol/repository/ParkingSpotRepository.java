package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.entity.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotEntity, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);

    // ✅ Para UPDATE (evitar conflito com o próprio registro)
    boolean existsByLicensePlateCarAndIdNot(String licensePlateCar, UUID id);

    boolean existsByParkingSpotNumberAndIdNot(String parkingSpotNumber, UUID id);

    boolean existsByApartmentAndBlockAndIdNot(String apartment, String block, UUID id);
}
