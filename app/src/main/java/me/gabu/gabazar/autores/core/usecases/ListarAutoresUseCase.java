package me.gabu.gabazar.autores.core.usecases;

import java.util.Collection;

import me.gabu.gabazar.autores.core.model.Autor;

public interface ListarAutoresUseCase {
    Collection<Autor> run(String nome);
}
