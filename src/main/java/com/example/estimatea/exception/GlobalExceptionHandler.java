package com.example.estimatea.exception;
import org.springframework.ui.Model;
import com.example.estimatea.dto.ErrorDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {

        //    NotFoundException        → 404 Not Found
        //    DuplicateKeyException    → 409 Conflict
        //    IllegalArgumentException → 400 Bad Request
        //    DataAccessException      → 500 Internal Server Error

    @ExceptionHandler(NotFoundException.class) // --> Handles 404 Not Found
    public String handlesNotFoundException(NotFoundException e, Model model) {
        model.addAttribute("errorDto", new ErrorDTO(404, e.getMessage()));
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class) // --> Handles 400 Bad Request
    public String handlesIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("errorDto", new ErrorDTO(400, e.getMessage()));
        return "error";
    }

        // Thrown by Spring/Jdbc automatically

    @ExceptionHandler(DuplicateKeyException.class) // --> Handles 409 Conflict
    public String handlesDuplicateKeyException(DuplicateKeyException e, Model model) {
        model.addAttribute("errorDto", new ErrorDTO(409, e.getMessage()));
        return "error";
    }

    @ExceptionHandler(DataAccessException.class) // --> Handles 500 Internal Server Error
    public String handlesDataAccessException(DataAccessException e, Model model) {
        model.addAttribute("errorDto", new ErrorDTO(500, e.getMessage()));
        return "error";
    }
}
