package tabiMax.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tabiMax.config.CustomExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomExpiredJwtException.class)
    public ResponseEntity<?> handleCustomExpiredJwtException(CustomExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Access token đã hết hạn. Vui lòng đăng nhập lại hoặc làm mới access token của bạn.");
    }
   
}
