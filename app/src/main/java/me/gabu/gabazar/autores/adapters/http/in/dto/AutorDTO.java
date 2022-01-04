package me.gabu.gabazar.autores.adapters.http.in.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AutorDTO {

    private String id;
    private String nome;
    private String nacionalidade;
    private String site;
    private String usuarioCriacao;
    private String usuarioAlteracao;
    private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date dataCriacao;
    private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date dataAlteracao;

}
