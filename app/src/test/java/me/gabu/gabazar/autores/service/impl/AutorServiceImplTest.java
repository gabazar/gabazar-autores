package me.gabu.gabazar.autores.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.core.usecases.ApagarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.AtualizarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.CriarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ListarAutoresUseCase;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {

    private static final String ID = UUID.randomUUID().toString();
    private static final String USUARIO = "Johnny";
    private static final String NOME = "nome";

    private @Mock CriarAutorUseCase criarUC;
    private @Mock ConsultarAutorUseCase consultarUC;
    private @Mock AtualizarAutorUseCase atualizarUC;
    private @Mock ListarAutoresUseCase listarUC;
    private @Mock ApagarAutorUseCase apagarUC;
    private @InjectMocks AutorServiceImpl service;

    private Autor autor = Autor.builder().build();

    @Test
    void criarAutor() {
        doReturn(autor).when(criarUC).run(autor, USUARIO);
        service.criarAutor(autor, USUARIO);
        verify(criarUC, times(1)).run(autor, USUARIO);
    }

    @Test
    void consultarAutor() {
        doReturn(autor).when(consultarUC).run(ID);
        service.consultarAutor(ID);
        verify(consultarUC, times(1)).run(ID);
    }

    @Test
    void atualizarAutor() {
        doReturn(autor).when(atualizarUC).run(autor, USUARIO);
        service.atualizarAutor(autor, USUARIO);
        verify(atualizarUC, times(1)).run(autor, USUARIO);
    }

    @Test
    void listarAutores() {
        doReturn(Arrays.asList(autor)).when(listarUC).run(NOME);
        service.listarAutores(NOME);
        verify(listarUC, times(1)).run(NOME);
    }

    @Test
    void apagarAutor() {
        doNothing().when(apagarUC).run(ID, USUARIO);
        service.apagarAutor(ID, USUARIO);
        verify(apagarUC, times(1)).run(ID, USUARIO);
    }
}
