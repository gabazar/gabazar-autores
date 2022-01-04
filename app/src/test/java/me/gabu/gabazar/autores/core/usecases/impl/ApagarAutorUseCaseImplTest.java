package me.gabu.gabazar.autores.core.usecases.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

@ExtendWith(MockitoExtension.class)
class ApagarAutorUseCaseImplTest {

    private static final String ID = UUID.randomUUID().toString();
    private static final String USUARIO = "Johnny";

    private @Mock AutorDAO dao;
    private @Mock ConsultarAutorUseCase consultarUC;
    private @InjectMocks ApagarAutorUseCaseImpl uc;

    private Autor autor = Autor.builder().build();

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(consultarUC);
    }

    @Test
    void run() {
        doReturn(autor).when(consultarUC).run(ID);
        doNothing().when(dao).delete(autor);

        uc.run(ID, USUARIO);

        verify(consultarUC, times(1)).run(ID);
        verify(dao, times(1)).delete(autor);
    }

}
