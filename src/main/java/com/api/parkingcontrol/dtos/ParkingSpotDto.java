package com.api.parkingcontrol.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDto {
    @Schema(
            description = "Número identificador da vaga",
            example = "A01",
            required = true
    )
    @NotBlank(message = "O número da vaga é obrigatório")
    private String parkingSpotNumber;


    @Schema(
            description = "Placa do carro (formato padrão: ABC1234)",
            example = "ABC1234",
            minLength = 7,
            maxLength = 7,
            required = true
    )
    @NotBlank(message = "A placa é obrigatória")
    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres")
    private String licensePlateCar;

    @Schema(
            description = "Marca do carro",
            example = "Toyota",
            required = true
    )
    @NotBlank(message = "A marca do carro é obrigatória")
    private String brandCar;

    @Schema(
            description = "Modelo do carro",
            example = "Corolla",
            required = true
    )
    @NotBlank(message = "O modelo do carro é obrigatório")
    private String modelCar;

    @Schema(
            description = "Cor do carro",
            example = "Preto",
            required = true
    )
    @NotBlank(message = "A cor do carro é obrigatória")
    private String colorCar;

    @Schema(
            description = "Nome do responsável pela vaga",
            example = "Victor Alves",
            required = true
    )
    @NotBlank(message = "O nome do responsável é obrigatório")
    private String responsibleName;

    @Schema(
            description = "Número do apartamento",
            example = "101",
            required = true
    )
    @NotBlank(message = "O número do apartamento é obrigatório")
    private String apartment;

    @Schema(
            description = "Bloco do apartamento",
            example = "Bloco A",
            required = true
    )
    @NotBlank(message = "O bloco é obrigatório")
    private String block;

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getResponsableName() {
        return responsibleName;
    }

    public void setResponsableName(String responsableName) {
        this.responsibleName = responsableName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
