package me.gabu.gabazar.autores.core.usecases;

import me.gabu.gabazar.autores.core.model.Autor;

public interface AtualizarAutorUseCase {
    Autor run(Autor autor, String usuario);
}
