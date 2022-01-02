package me.gabu.gabazar.autores.core.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.gabu.gabazar.autores.service.validations.Create;
import me.gabu.gabazar.autores.service.validations.Update;

@Getter
@Setter
@ToString
public class Autor {
    private @NotNull(groups = Update.class) String id;
    private @NotNull(groups = { Update.class, Create.class }) String nome;
    private @NotNull(groups = { Update.class, Create.class }) String nacionalidade;
    private @NotNull(groups = { Update.class, Create.class }) String site;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String usuarioCriacao;
    private String usuarioAlteracao;
}
