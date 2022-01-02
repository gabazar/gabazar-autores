package me.gabu.gabazar.autores.core.usecases.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.ListarAutoresUseCase;

@Service
public class ListarAutoresUseCaseImpl implements ListarAutoresUseCase {

    private @Autowired AutorDAO dao;

    @Override
    public Collection<Autor> run(String nome) {

        if (StringUtils.hasLength(nome))
            return dao.findByNome(nome);

        return dao.listAll();
    }

}
