package me.gabu.gabazar.autores.core.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gabu.gabazar.autores.service.validations.Create;
import me.gabu.gabazar.autores.service.validations.Update;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    private static final String MSG_NAC_NULO = "[nacionalidade não deve ser nulo]";
    private static final String MSG_SITE_NULO = "[site não deve ser nulo]";
    private static final String MSG_NOME_NULO = "[nome não deve ser nulo]";
    private static final String MSG_ID_NULO = "[id não deve ser nulo]";

    private @NotNull(groups = Update.class, message = MSG_ID_NULO) String id;
    private @NotNull(groups = { Update.class, Create.class }, message = MSG_NOME_NULO) String nome;
    private @NotNull(groups = { Update.class, Create.class }, message = MSG_NAC_NULO) String nacionalidade;
    private @NotNull(groups = { Update.class, Create.class }, message = MSG_SITE_NULO) String site;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String usuarioCriacao;
    private String usuarioAlteracao;
}
