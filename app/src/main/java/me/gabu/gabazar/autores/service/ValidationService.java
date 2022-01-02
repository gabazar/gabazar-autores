package me.gabu.gabazar.autores.service;

import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

public interface ValidationService {
    public void validate(Autor autor, ValidationEnum validation);
}
