package me.gabu.gabazar.autores.core.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.AtualizarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;
import me.gabu.gabazar.autores.service.ValidationService;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

@Slf4j
@Service
public class AtualizarAutorUseCaseImpl implements AtualizarAutorUseCase {

    private @Autowired AutorDAO dao;
    private @Autowired ConsultarAutorUseCase consultarUC;
    private @Autowired ValidationService validator;

    @Override
    public Autor run(Autor autor, String usuario) {
        Autor registroAnterior = consultarUC.run(autor.getId());

        validator.validate(autor, ValidationEnum.UPDATE);
        log.info("[USECASE] [UPDATE]\nRegistro anterior: {} \nRegistro recebido: {}", registroAnterior, autor);

        autor.setUsuarioAlteracao(usuario);
        autor.setUsuarioCriacao(registroAnterior.getUsuarioCriacao());
        autor.setDataCriacao(registroAnterior.getDataCriacao());

        return dao.update(autor);
    }

}
