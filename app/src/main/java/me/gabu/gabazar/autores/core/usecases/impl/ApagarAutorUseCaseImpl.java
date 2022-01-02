package me.gabu.gabazar.autores.core.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.usecases.ApagarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;

@Slf4j
@Service
public class ApagarAutorUseCaseImpl implements ApagarAutorUseCase {

    private @Autowired AutorDAO dao;
    private @Autowired ConsultarAutorUseCase consultarUC;

    @Override
    public void run(String autorId, String usuario) {
        log.info("[USECASE] [DELETE] {}", autorId);

        dao.delete(consultarUC.run(autorId));
    }

}
