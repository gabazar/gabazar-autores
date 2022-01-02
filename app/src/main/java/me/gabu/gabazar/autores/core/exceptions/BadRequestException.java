package me.gabu.gabazar.autores.core.exceptions;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequestException extends RuntimeException {

    @Getter
    private Collection<String> messages;

    public BadRequestException(String mensagem) {
        super(mensagem);
        messages = Arrays.asList(mensagem);
    }

    public BadRequestException(Collection<String> mensagens) {
        messages = mensagens;
    }

    private static final long serialVersionUID = 1L;

}
