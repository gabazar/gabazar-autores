package me.gabu.gabazar.autores.core.exceptions;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErrorData {
    private Collection<String> errors;
    private String path;
    private int code;
    private HttpStatus status;
}
