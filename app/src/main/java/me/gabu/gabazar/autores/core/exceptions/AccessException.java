package me.gabu.gabazar.autores.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AccessException extends RuntimeException{

    public AccessException(String mensagem) {
        super(mensagem);
    }

    private static final long serialVersionUID = 1L;

}
