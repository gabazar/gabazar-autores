package me.gabu.gabazar.autores;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.gabu.gabazar.autores.adapters.http.in.AutorController;
import me.gabu.gabazar.autores.core.usecases.ApagarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.AtualizarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ConsultarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.CriarAutorUseCase;
import me.gabu.gabazar.autores.core.usecases.ListarAutoresUseCase;
import me.gabu.gabazar.autores.service.AutorService;
import me.gabu.gabazar.autores.service.TokenService;
import me.gabu.gabazar.autores.service.ValidationService;

@SpringBootTest
class AutoresApplicationTests {

    private @Autowired AutorController controller;
    private @Autowired AutorService service;
    private @Autowired TokenService token;
    private @Autowired ValidationService validation;
    private @Autowired ApagarAutorUseCase apagarUC;
    private @Autowired AtualizarAutorUseCase atualizarUC;
    private @Autowired ConsultarAutorUseCase consultarUC;
    private @Autowired CriarAutorUseCase criarUC;
    private @Autowired ListarAutoresUseCase listarUC;

    @Test
    void contextLoads() {
        assertNotNull(controller);
        assertNotNull(service);
        assertNotNull(token);
        assertNotNull(validation);
        assertNotNull(apagarUC);
        assertNotNull(atualizarUC);
        assertNotNull(consultarUC);
        assertNotNull(criarUC);
        assertNotNull(listarUC);
    }

}
