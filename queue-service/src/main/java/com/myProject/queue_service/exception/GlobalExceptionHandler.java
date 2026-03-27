package com.myProject.queue_service.exception;


import com.myProject.queue_service.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleUserExists(UserAlreadyExistException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse(ex.getMessage(), false));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse(ex.getMessage(), false));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ApiResponse("Something went wrong", false));
    }
}
