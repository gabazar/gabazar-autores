package me.gabu.gabazar.autores.adapters.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import me.gabu.gabazar.autores.adapters.data.dao.AutorDAO;
import me.gabu.gabazar.autores.adapters.data.entity.AutorEntity;
import me.gabu.gabazar.autores.adapters.data.entity.mapper.AutorEntityMapper;
import me.gabu.gabazar.autores.adapters.data.repository.AutorRepository;
import me.gabu.gabazar.autores.core.exceptions.NotFoundException;
import me.gabu.gabazar.autores.core.model.Autor;

@Slf4j
@Service
public class AutorDAOImpl implements AutorDAO {

    private @Autowired AutorRepository repository;
    private AutorEntityMapper mapper = AutorEntityMapper.INSTANCE;

    @Override
    public Autor findById(String id) {
        AutorEntity enditoraEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado"));
        return mapper.autorEntityToAutor(enditoraEntity);
    }

    @Override
    public Autor save(Autor autor) {
        AutorEntity enditoraEntity = mapper.autorToAutorEntity(autor);
        log.info("[DAO] [PERSIST] [{}]", autor);
        return mapper.autorEntityToAutor(repository.save(enditoraEntity));
    }

    @Override
    public Collection<Autor> listAll() {
        return mapper.autorEntityToAutor(repository.findAll());
    }

    @Override
    public Collection<Autor> findByNome(String name) {
        return mapper.autorEntityToAutor(repository.findByNome(name));
    }

    @Override
    public Autor update(Autor autor) {
        AutorEntity enditoraEntity = mapper.autorToAutorEntity(autor);
        log.info("[DAO] [UPDATE] [{}]", autor);
        return mapper.autorEntityToAutor(repository.save(enditoraEntity));
    }

    @Override
    public void delete(Autor autor) {
        repository.deleteById(autor.getId());
    }

}
