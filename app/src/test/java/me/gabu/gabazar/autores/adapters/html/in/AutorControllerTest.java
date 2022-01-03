package me.gabu.gabazar.autores.adapters.html.in;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import me.gabu.gabazar.autores.adapters.html.in.dto.AutorDTO;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.AutorService;
import me.gabu.gabazar.autores.service.TokenService;

@ExtendWith(MockitoExtension.class)
class AutorControllerTest {

    private static final String NOME_AUTOR = "NOME AUTOR";
    private static final String ID_AUTOR = UUID.randomUUID().toString();
    private static final String TOKEN = UUID.randomUUID().toString();
    private static final String USUARIO = "USUARIO";

    private AutorDTO dto;
    private Autor model;

    private @Mock AutorService service;
    private @Mock TokenService tokenService;

    private @InjectMocks @Spy AutorController controller;

    @BeforeEach
    public void beforeEach() {
        dto = new AutorDTO();
        model = new Autor();
    }

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(service);
        verifyNoMoreInteractions(tokenService);
    }

    @Test
    void post() {
        doNothing().when(tokenService).validaToken(TOKEN);
        when(tokenService.recuperarUsuario(TOKEN)).thenReturn(USUARIO);
        when(controller.toModel(dto)).thenReturn(model);
        when(service.criarAutor(model, USUARIO)).thenReturn(model);
        when(controller.toDTO(model)).thenReturn(dto);

        controller.post(dto, TOKEN);

        verify(tokenService, times(1)).validaToken(TOKEN);
        verify(tokenService, times(1)).recuperarUsuario(TOKEN);
        verify(service, times(1)).criarAutor(model, USUARIO);
    }

    @Test
    void getByID() {
        doNothing().when(tokenService).validaToken(TOKEN);
        when(service.consultarAutor(ID_AUTOR)).thenReturn(model);

        controller.getByID(ID_AUTOR, TOKEN);

        verify(tokenService, times(1)).validaToken(TOKEN);
        verify(service, times(1)).consultarAutor(ID_AUTOR);
    }

    @Test
    void put() {
        doNothing().when(tokenService).validaToken(TOKEN);
        when(controller.toModel(dto)).thenReturn(model);
        when(tokenService.recuperarUsuario(TOKEN)).thenReturn(USUARIO);
        when(service.atualizarAutor(model, USUARIO)).thenReturn(model);

        AutorDTO response = controller.put(ID_AUTOR, TOKEN, dto);

        assertEquals(ID_AUTOR, response.getId());

        verify(tokenService, times(1)).validaToken(TOKEN);
        verify(tokenService, times(1)).recuperarUsuario(TOKEN);
        verify(service, times(1)).atualizarAutor(model, USUARIO);
    }

    @Test
    void delete() {
        doNothing().when(tokenService).validaToken(TOKEN);
        when(tokenService.recuperarUsuario(TOKEN)).thenReturn(USUARIO);
        doNothing().when(service).apagarAutor(ID_AUTOR, USUARIO);

        ResponseEntity<AutorDTO> delete = controller.delete(ID_AUTOR, TOKEN);

        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());

        verify(tokenService, times(1)).validaToken(TOKEN);
        verify(tokenService, times(1)).recuperarUsuario(TOKEN);
        verify(service, times(1)).apagarAutor(ID_AUTOR, USUARIO);
    }

    @Test
    void get() {
        doNothing().when(tokenService).validaToken(TOKEN);
        when(service.listarAutores(NOME_AUTOR)).thenReturn(Arrays.asList(model));

        Collection<AutorDTO> lista = controller.get(NOME_AUTOR, TOKEN);

        assertEquals(1, lista.size());

        verify(tokenService, times(1)).validaToken(TOKEN);
        verify(service, times(1)).listarAutores(NOME_AUTOR);
    }
}
