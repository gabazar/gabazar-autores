package me.gabu.gabazar.autores.core.usecases.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.core.model.Autor;

@ExtendWith(MockitoExtension.class)
class ListarAutoresUseCaseImplTest {

    private static final String NOME = "NOME";

    private @Mock AutorDAO dao;
    private @InjectMocks ListarAutoresUseCaseImpl uc;

    private Autor build = Autor.builder().build();

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(dao);
    }

    @Test
    void runListAll() {
        when(dao.listAll()).thenReturn(Arrays.asList(build));

        Collection<Autor> run = uc.run(null);

        assertEquals(1, run.size());
        verify(dao, times(1)).listAll();
    }

    @Test
    void runFindByNome() {
        when(dao.findByNome(NOME)).thenReturn(Arrays.asList(build));

        Collection<Autor> run = uc.run(NOME);

        assertEquals(1, run.size());
        verify(dao, times(1)).findByNome(NOME);
    }

}
