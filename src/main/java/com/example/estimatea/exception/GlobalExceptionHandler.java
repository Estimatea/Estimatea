package com.example.estimatea.exception;
import com.example.estimatea.dto.ErrorDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        //    NotFoundException        → 404 Not Found
        //    DuplicateKeyException    → 409 Conflict
        //    IllegalArgumentException → 400 Bad Request
        //    DataAccessException      → 500 Internal Server Error

    @ExceptionHandler(NotFoundException.class) // --> Handles 404 Not Found
    public ResponseEntity<ErrorDTO> handlesNotFoundException(NotFoundException e) {
        ErrorDTO error = new ErrorDTO(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class) // --> Handles 400 Bad Request
    public ResponseEntity<ErrorDTO> handlesIllegalArgumentException(IllegalArgumentException e) {
        ErrorDTO error = new ErrorDTO(400, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

        // Thrown by Spring/Jdbc automatically
    @ExceptionHandler(DuplicateKeyException.class) // --> Handles 409 Conflict
    public ResponseEntity<ErrorDTO> handlesDuplicateKeyException(DuplicateKeyException e) {
        ErrorDTO error = new ErrorDTO(409, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DataAccessException.class) // --> Handles 500 Internal Server Error
    public ResponseEntity<ErrorDTO> handlesDataAccessException(DataAccessException e) {
        ErrorDTO error = new ErrorDTO(500, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
