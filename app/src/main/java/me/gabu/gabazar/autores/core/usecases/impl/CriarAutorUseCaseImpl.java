package me.gabu.gabazar.autores.core.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.CriarAutorUseCase;
import me.gabu.gabazar.autores.service.ValidationService;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

@Slf4j
@Service
public class CriarAutorUseCaseImpl implements CriarAutorUseCase {

    private @Autowired AutorDAO dao;
    private @Autowired ValidationService validator;

    @Override
    public Autor run(Autor autor, String usuario) {
        log.info("[USECASE] [CREATE] {}", autor);
        validator.validate(autor, ValidationEnum.CREATE);
        autor.setUsuarioCriacao(usuario);
        return dao.save(autor);
    }

}
