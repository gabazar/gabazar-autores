package me.gabu.gabazar.autores.adapters.http.in;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.core.exceptions.AccessException;
import me.gabu.gabazar.autores.core.exceptions.BadRequestException;
import me.gabu.gabazar.autores.core.exceptions.ErrorData;
import me.gabu.gabazar.autores.core.exceptions.NotFoundException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorData> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return buildErrorData(ex, ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorData> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return buildErrorData(ex, ex.getMessages(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AccessException.class)
    public final ResponseEntity<ErrorData> handleAccessException(AccessException ex, WebRequest request) {
        return buildErrorData(ex, ex.getMessage(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorData> handleAllExceptions(Exception ex, WebRequest request) {
        return buildErrorData(ex, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorData> buildErrorData(Exception ex, String message, HttpStatus status,
            WebRequest request) {
        return buildErrorData(ex, Arrays.asList(message), status, request);
    }

    private ResponseEntity<ErrorData> buildErrorData(Exception ex, Collection<String> message, HttpStatus status,
            WebRequest request) {
        ErrorData errorData = new ErrorData(message, getPath(request), status.value(), status);
        log.error("[EXCEPTION] [{}] {}", ex.getClass().getSimpleName(), errorData);
        return new ResponseEntity<>(errorData, status);
    }

    protected String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getServletPath();
    }
}