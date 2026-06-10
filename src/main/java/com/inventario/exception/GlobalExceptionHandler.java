package com.inventario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TipoEquipoNotFoundException.class)
    public ResponseEntity<ApiError> handleTipoEquipoNotFound(TipoEquipoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MarcaNotFoundException.class)
    public ResponseEntity<ApiError> handleMarcaNotFound(MarcaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ApiError> handleModeloNotFound(ModeloNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NombreTipoEquipoDuplicadoException.class)
    public ResponseEntity<ApiError> handleNombreTipoEquipoDuplicado(NombreTipoEquipoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiError.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NombreMarcaDuplicadoException.class)
    public ResponseEntity<ApiError> handleNombreMarcaDuplicado(NombreMarcaDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiError.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<ApiError> handleAreaNotFound(AreaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NombreAreaDuplicadoException.class)
    public ResponseEntity<ApiError> handleNombreAreaDuplicado(NombreAreaDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiError.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(SerialDuplicadoException.class)
    public ResponseEntity<ApiError> handleSerialDuplicado(SerialDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiError.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Error de validación")
                        .errors(errores)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Parámetro requerido faltante: " + ex.getParameterName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Valor inválido para '" + ex.getName() + "'. Formato esperado: yyyy-MM-ddTHH:mm:ss")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMalformedJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("El cuerpo de la petición es inválido o está malformado")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                ApiError.builder()
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message("Método HTTP no permitido: " + ex.getMethod())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Recurso no encontrado: " + ex.getResourcePath())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Error interno del servidor")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
