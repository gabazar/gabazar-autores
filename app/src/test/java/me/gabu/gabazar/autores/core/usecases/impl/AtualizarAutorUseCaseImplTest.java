package me.gabu.gabazar.autores.core.usecases.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;
import me.gabu.gabazar.autores.service.ValidationService;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

@ExtendWith(MockitoExtension.class)
class AtualizarAutorUseCaseImplTest {

    private static final String ID = UUID.randomUUID().toString();
    private static final String USUARIO = "Johnny";

    private @Mock AutorDAO dao;
    private @Mock ValidationService validation;
    private @Mock ConsultarAutorUseCase consultarUC;
    private @InjectMocks AtualizarAutorUseCaseImpl uc;

    private Autor autor = Autor.builder().build();
    private Autor autorRegistroAnterior = Autor.builder().build();

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(validation);
        verifyNoMoreInteractions(consultarUC);
    }

    @Test
    void run() {
        Date dataCriacao = new Date();
        autorRegistroAnterior.setDataCriacao(dataCriacao);
        autorRegistroAnterior.setUsuarioCriacao(USUARIO);

        doReturn(autorRegistroAnterior).when(consultarUC).run(ID);
        doNothing().when(validation).validate(autor, ValidationEnum.UPDATE);
        when(dao.update(autor)).thenReturn(autor);

        autor.setId(ID);
        Autor run = uc.run(autor, USUARIO);

        assertEquals(dataCriacao, run.getDataCriacao());
        assertEquals(USUARIO, run.getUsuarioCriacao());
        assertEquals(USUARIO, run.getUsuarioAlteracao());

        verify(consultarUC, times(1)).run(ID);
        verify(validation, times(1)).validate(autor, ValidationEnum.UPDATE);
        verify(dao, times(1)).update(autor);
    }

}
