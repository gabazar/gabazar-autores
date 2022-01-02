package me.gabu.gabazar.autores.adapters.html.in;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.html.in.dto.AutorDTO;
import me.gabu.gabazar.autores.adapters.html.in.dto.mapper.AutorDTOMapper;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.AutorService;
import me.gabu.gabazar.autores.service.TokenService;

@Slf4j
@Controller
@RequestMapping("/autores")
public class AutorController {

    private @Autowired AutorService service;
    private @Autowired TokenService tokenService;

    private AutorDTOMapper mapper = AutorDTOMapper.INSTANCE;

    @PostMapping(produces = "application/json")
    public @ResponseBody AutorDTO post(@RequestBody AutorDTO autorDTO, @RequestHeader("token") String token) {
        log.info("[POST] [/autores] Request: {}", autorDTO);

        validaToken(token);

        Autor autor = mapper.autorDtoToAutor(autorDTO);
        Autor autorCriada = service.criarAutor(autor, getUsuario(token));

        return mapper.autorToAutorDto(autorCriada);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody AutorDTO getByID(@PathVariable("id") String id, @RequestHeader("token") String token) {
        log.info("[GET] [/autores/{}]", id);

        validaToken(token);

        return mapper.autorToAutorDto(service.consultarAutor(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody AutorDTO put(@PathVariable("id") String id, @RequestHeader("token") String token,
            @RequestBody AutorDTO autorDTO) {
        log.info("[PUT] [/autores/{}] Request: {}", id, autorDTO);

        validaToken(token);

        Autor autor = mapper.autorDtoToAutor(autorDTO);
        autor.setId(id);

        return mapper.autorToAutorDto(service.atualizarAutor(autor, getUsuario(token)));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AutorDTO> delete(@PathVariable("id") String id, @RequestHeader("token") String token) {
        log.info("[DELETE] [/autores/{}]", id);

        validaToken(token);

        service.apagarAutor(id, getUsuario(token));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping
    public @ResponseBody Collection<AutorDTO> get(@RequestParam(required = false) String nome,
            @RequestHeader("token") String token) {
        validaToken(token);

        return mapper.autorToAutorDto(service.listarAutores(nome));
    }

    private String getUsuario(String token) {
        return tokenService.recuperarUsuario(token);
    }

    private void validaToken(String token) {
        tokenService.validaToken(token);
    }
}
