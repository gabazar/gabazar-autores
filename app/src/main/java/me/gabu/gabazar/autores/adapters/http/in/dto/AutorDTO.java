package me.gabu.gabazar.autores.adapters.http.in.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("Autor")
public class AutorDTO {

    @ApiModelProperty("ID do autor no padrão UUID")
    private String id;
    @ApiModelProperty("Nome do autor")
    private String nome;
    @ApiModelProperty("Nacionalidade do autor")
    private String nacionalidade;
    @ApiModelProperty("Site ou link de rede social do autor")
    private String site;

    @ApiModelProperty("Usuario responsavel pelo cadastro do autor no sistema")
    private String usuarioCriacao;
    @ApiModelProperty("Usuario responsavel pela ultima alteração do autor no sistema")
    private String usuarioAlteracao;
    @ApiModelProperty("Data do cadastro do autor no sistema")
    private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date dataCriacao;
    @ApiModelProperty("Data da ultima alteração do autor no sistema")
    private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date dataAlteracao;

}
