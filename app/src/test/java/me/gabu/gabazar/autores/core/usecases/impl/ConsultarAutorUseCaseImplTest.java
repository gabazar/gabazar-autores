package me.gabu.gabazar.autores.core.usecases.impl;

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

@ExtendWith(MockitoExtension.class)
class ConsultarAutorUseCaseImplTest {

    private static final String ID = UUID.randomUUID().toString();

    private @Mock AutorDAO dao;
    private @InjectMocks ConsultarAutorUseCaseImpl uc;

    private Autor autor = Autor.builder().build();

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(dao);
    }

    @Test
    void test() {
        doReturn(autor).when(dao).findById(ID);

        uc.run(ID);

        verify(dao, times(1)).findById(ID);
    }

}
