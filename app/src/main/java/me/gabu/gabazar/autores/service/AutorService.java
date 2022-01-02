
package me.gabu.gabazar.autores.service;

import java.util.Collection;

import me.gabu.gabazar.autores.core.model.Autor;

public interface AutorService {

    public Autor criarAutor(Autor autor, String usuario);

    public Autor consultarAutor(String autorId);

    public Autor atualizarAutor(Autor autor, String usuario);

    public Collection<Autor> listarAutores(String nome);

    public void apagarAutor(String autorId, String usuario);

}
