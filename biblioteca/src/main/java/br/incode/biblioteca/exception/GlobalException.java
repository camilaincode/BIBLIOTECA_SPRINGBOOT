package br.incode.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.incode.biblioteca.payload.response.ApiResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(GeneroException.class)
    public ResponseEntity<ApiResponse> handleGeneroException(GeneroException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getMessage(), false));
    }
    
}
