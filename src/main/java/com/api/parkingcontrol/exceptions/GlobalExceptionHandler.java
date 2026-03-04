package com.api.parkingcontrol.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<Object> handleBusinessException(BusinessException ex) {

            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.CONFLICT.value());
            body.put("error", "Business Exception");
            body.put("message", ex.getMessage());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleGenericException(Exception ex) {

            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", "Internal Server Error");
            body.put("message", "Unexpected error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
