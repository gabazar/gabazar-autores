package me.gabu.gabazar.autores.adapters.data.dao;

import java.util.Collection;

import me.gabu.gabazar.autores.core.model.Autor;

public interface AutorDAO {

    public Autor findById(String id);
    public Autor save(Autor autor);
    public Collection<Autor> listAll();
    public Collection<Autor> findByNome(String name);
    public Autor update(Autor autor);
    public void delete(Autor autor);
}
