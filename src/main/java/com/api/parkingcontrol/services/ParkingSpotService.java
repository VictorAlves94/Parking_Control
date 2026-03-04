package com.api.parkingcontrol.services;

import com.api.parkingcontrol.entity.ParkingSpotEntity;
import com.api.parkingcontrol.exceptions.BusinessException;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotRepository getParkingSpotRepository() {
        return parkingSpotRepository;
    }

    public Page<ParkingSpotEntity> findAll(Pageable pageable ){
        return parkingSpotRepository.findAll(pageable);
    }

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }
    @Transactional
    public ParkingSpotEntity save(ParkingSpotEntity parkingSpotEntity) {

        if (parkingSpotEntity.getId() == null) {
            // CREATE
            if (parkingSpotRepository.existsByLicensePlateCar(parkingSpotEntity.getLicensePlateCar())) {
                throw new BusinessException("License Plate Car is already in use!");
            }

            if (parkingSpotRepository.existsByParkingSpotNumber(parkingSpotEntity.getParkingSpotNumber())) {
                throw new BusinessException("Parking Spot is already in use!");
            }

            if (parkingSpotRepository.existsByApartmentAndBlock(
                    parkingSpotEntity.getApartment(),
                    parkingSpotEntity.getBlock())) {

                throw new BusinessException("Parking Spot already registered for this apartment/block!");
            }

        } else {

            if (parkingSpotRepository.existsByLicensePlateCarAndIdNot(
                    parkingSpotEntity.getLicensePlateCar(),
                    parkingSpotEntity.getId())) {

                throw new BusinessException("License Plate Car is already in use!");
            }


        }

        return parkingSpotRepository.save(parkingSpotEntity);
    }


    public Optional<ParkingSpotEntity> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }
    @Transactional
    public void delete(ParkingSpotEntity parkingSpotEntity) {
        parkingSpotRepository.delete(parkingSpotEntity);

    }
}

