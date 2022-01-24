package me.gabu.gabazar.autores.adapters.http.in;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.http.in.dto.AutorDTO;
import me.gabu.gabazar.autores.adapters.http.in.dto.mapper.AutorDTOMapper;
import me.gabu.gabazar.autores.core.model.Autor;
import me.gabu.gabazar.autores.service.AutorService;
import me.gabu.gabazar.autores.service.TokenService;

@Slf4j
@RestController
@RequestMapping("/autores")
public class AutorController {

    private @Autowired AutorService service;
    private @Autowired TokenService tokenService;

    private AutorDTOMapper mapper = AutorDTOMapper.INSTANCE;

    @PostMapping
    @ApiOperation(value = "Cadastra novo Autor")
    public @ResponseBody AutorDTO post(@RequestBody AutorDTO autorDTO, @RequestHeader("token") String token) {
        log.info("[POST] [/autores] Request: {}", autorDTO);

        tokenService.validaToken(token);

        Autor autor = toModel(autorDTO);
        Autor autorCriado = service.criarAutor(autor, tokenService.recuperarUsuario(token));

        return toDTO(autorCriado);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Consulta autor já cadastrado pelo ID")
    public @ResponseBody AutorDTO getByID(@PathVariable("id") String id, @RequestHeader("token") String token) {
        log.info("[GET] [/autores/{}]", id);

        tokenService.validaToken(token);
        return toDTO(service.consultarAutor(id));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Sobrescreve os dados de um autor cadastrado")
    public @ResponseBody AutorDTO put(@PathVariable("id") String id, @RequestHeader("token") String token,
            @RequestBody AutorDTO autorDTO) {
        log.info("[PUT] [/autores/{}] Request: {}", id, autorDTO);

        tokenService.validaToken(token);
        Autor autor = toModel(autorDTO);
        autor.setId(id);

        return toDTO(service.atualizarAutor(autor, tokenService.recuperarUsuario(token)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Apaga o registro de um autor")
    public ResponseEntity<AutorDTO> delete(@PathVariable("id") String id, @RequestHeader("token") String token) {
        log.info("[DELETE] [/autores/{}]", id);

        tokenService.validaToken(token);
        service.apagarAutor(id, tokenService.recuperarUsuario(token));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos os autores cadastrados, podendo ser filtrado do nome do autor")
    public @ResponseBody Collection<AutorDTO> get(@RequestParam(required = false) String nome,
            @RequestHeader("token") String token) {
        tokenService.validaToken(token);

        return toDTO(service.listarAutores(nome));
    }

    protected Collection<AutorDTO> toDTO(Collection<Autor> autorCriada) {
        return mapper.autorToAutorDto(autorCriada);
    }

    protected AutorDTO toDTO(Autor autorCriada) {
        return mapper.autorToAutorDto(autorCriada);
    }

    protected Autor toModel(AutorDTO autorDTO) {
        return mapper.autorDtoToAutor(autorDTO);
    }

}
