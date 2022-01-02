package me.gabu.gabazar.autores.core.usecases;

import me.gabu.gabazar.autores.core.model.Autor;

public interface ConsultarAutorUseCase {
    Autor run(String autorId);
}
