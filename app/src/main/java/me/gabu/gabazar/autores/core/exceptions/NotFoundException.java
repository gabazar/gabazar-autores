package me.gabu.gabazar.autores.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem) {
        super(mensagem);
    }

    private static final long serialVersionUID = 1L;

}
