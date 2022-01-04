package me.gabu.gabazar.autores.adapters.html.in;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import me.gabu.gabazar.autores.core.exceptions.AccessException;
import me.gabu.gabazar.autores.core.exceptions.BadRequestException;
import me.gabu.gabazar.autores.core.exceptions.ErrorData;
import me.gabu.gabazar.autores.core.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    private static final String EX_400 = "BAD_REQUEST";
    private static final String EX_400_ALT = "BAD_REQUEST_2";
    private static final String EX_401 = "UNAUTHORIZED";
    private static final String EX_404 = "NOT_FOUND";
    private static final String EX_500 = "GENERIC";
    private static final String PATH = "/autores";

    private @Mock WebRequest request;
    private @Spy @InjectMocks ControllerExceptionHandler handler;

    @Test
    void handleAllExceptions() {
        doReturn(PATH).when(handler).getPath(request);

        Exception ex = new Exception(EX_500);
        ResponseEntity<ErrorData> errorData = handler.handleAllExceptions(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorData.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorData.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorData.getBody().getStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorData.getBody().getCode());

        assertEquals(1, errorData.getBody().getErrors().size());
        assertEquals(PATH, errorData.getBody().getPath());
        assertEquals(EX_500, errorData.getBody().getErrors().iterator().next());
    }

    @Test
    void handleNotFoundException() {
        doReturn(PATH).when(handler).getPath(request);

        NotFoundException ex = new NotFoundException(EX_404);
        ResponseEntity<ErrorData> errorData = handler.handleNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, errorData.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), errorData.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, errorData.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND.value(), errorData.getBody().getCode());

        assertEquals(1, errorData.getBody().getErrors().size());
        assertEquals(PATH, errorData.getBody().getPath());
        assertEquals(EX_404, errorData.getBody().getErrors().iterator().next());
    }

    @Test
    void handleBadRequestException() {
        doReturn(PATH).when(handler).getPath(request);

        BadRequestException ex = new BadRequestException(EX_400);
        ResponseEntity<ErrorData> errorData = handler.handleBadRequestException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, errorData.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorData.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, errorData.getBody().getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorData.getBody().getCode());

        assertEquals(1, errorData.getBody().getErrors().size());
        assertEquals(PATH, errorData.getBody().getPath());
        assertEquals(EX_400, errorData.getBody().getErrors().iterator().next());
    }

    @Test
    void handleBadRequestExceptionArray() {
        doReturn(PATH).when(handler).getPath(request);

        BadRequestException ex = new BadRequestException(Arrays.asList(EX_400, EX_400_ALT));
        ResponseEntity<ErrorData> errorData = handler.handleBadRequestException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, errorData.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorData.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, errorData.getBody().getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorData.getBody().getCode());

        assertEquals(2, errorData.getBody().getErrors().size());
        assertEquals(PATH, errorData.getBody().getPath());
    }

    @Test
    void handleAccessException() {
        doReturn(PATH).when(handler).getPath(request);

        AccessException ex = new AccessException(EX_401);
        ResponseEntity<ErrorData> errorData = handler.handleAccessException(ex, request);

        assertEquals(HttpStatus.UNAUTHORIZED, errorData.getStatusCode());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorData.getStatusCodeValue());
        assertEquals(HttpStatus.UNAUTHORIZED, errorData.getBody().getStatus());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorData.getBody().getCode());

        assertEquals(1, errorData.getBody().getErrors().size());
        assertEquals(PATH, errorData.getBody().getPath());
        assertEquals(EX_401, errorData.getBody().getErrors().iterator().next());
    }

}
