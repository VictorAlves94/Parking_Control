package com.api.parkingcontrol.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta padrão de erro da API")
public class ApiError {
    @Schema(example = "2026-03-23T12:00:00")
    private LocalDateTime timestamp;

    @Schema(example = "409")
    private int status;

    @Schema(example = "Business Exception")
    private String error;

    @Schema(example = "Placa já cadastrada")
    private String message;

    @Schema(example = "/parking-spot")
    private String path;

    public ApiError(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }



}
