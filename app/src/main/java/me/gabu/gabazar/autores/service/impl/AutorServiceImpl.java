package me.gabu.gabazar.autores.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.ApagarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.AtualizarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.CriarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ListarAutoresUseCase;
import me.gabu.gabazar.autores.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

    private @Autowired CriarAutorUseCase criarAutor;
    private @Autowired ConsultarAutorUseCase consultarAutor;
    private @Autowired AtualizarAutorUseCase atualizarAutor;
    private @Autowired ListarAutoresUseCase listarAutores;
    private @Autowired ApagarAutorUseCase apagarAutor;

    @Override
    public Autor criarAutor(Autor autor, String usuario) {
        return criarAutor.run(autor, usuario);
    }

    @Override
    public Autor consultarAutor(String autorId) {
        return consultarAutor.run(autorId);
    }

    @Override
    public Autor atualizarAutor(Autor autor, String usuario) {
        return atualizarAutor.run(autor, usuario);
    }

    @Override
    public Collection<Autor> listarAutores(String nome) {
        return listarAutores.run(nome);
    }

    @Override
    public void apagarAutor(String autorId, String usuario) {
        apagarAutor.run(autorId, usuario);
    }

}
