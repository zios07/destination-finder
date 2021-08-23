package com.afkl.cases.df.controllers.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("NOT.FOUND", throwable.getLocalizedMessage()));
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class ErrorResponse {
    private String code;
    private String message;
}
