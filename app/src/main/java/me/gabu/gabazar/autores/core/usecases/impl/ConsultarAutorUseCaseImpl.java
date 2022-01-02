package me.gabu.gabazar.autores.core.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;

@Service
public class ConsultarAutorUseCaseImpl implements ConsultarAutorUseCase {

    private @Autowired AutorDAO dao;

    @Override
    public Autor run(String autorId) {
        return dao.findById(autorId);
    }

}
