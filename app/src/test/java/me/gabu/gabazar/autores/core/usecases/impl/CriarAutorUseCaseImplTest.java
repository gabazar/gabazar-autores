package me.gabu.gabazar.autores.core.usecases.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.ValidationService;
import me.gabu.gabazar.autores.service.validations.ValidationEnum;

@ExtendWith(MockitoExtension.class)
class CriarAutorUseCaseImplTest {

    private static final String USUARIO = "Johnny";

    private @Mock AutorDAO dao;
    private @Mock ValidationService validation;
    private @InjectMocks CriarAutorUseCaseImpl uc;

    private Autor autor = Autor.builder().build();

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(validation);
    }

    @Test
    void run() {
        when(dao.save(autor)).thenReturn(autor);
        doNothing().when(validation).validate(autor, ValidationEnum.CREATE);

        uc.run(autor, USUARIO);

        verify(dao, times(1)).save(autor);
        verify(validation, times(1)).validate(autor, ValidationEnum.CREATE);
    }

}
