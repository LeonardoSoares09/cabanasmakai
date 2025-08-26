package com.cabanasmakai.app.handler;

import com.cabanasmakai.app.adapters.rest.dto.exceptions.ApiErrorResponse;
import com.cabanasmakai.app.exceptions.ClienteNaoEncontradoException;
import com.cabanasmakai.app.exceptions.ErroInternoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse("Erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErroInternoException.class)
    public ResponseEntity<ApiErrorResponse> handleErroInterno(ErroInternoException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
