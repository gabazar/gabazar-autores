package me.gabu.gabazar.autores.adapters.data.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import me.gabu.gabazar.autores.adapters.data.entity.AutorEntity;
import me.gabu.gabazar.autores.adapters.data.entity.mapper.AutorEntityMapper;
import me.gabu.gabazar.autores.adapters.data.repository.AutorRepository;
import me.gabu.gabazar.autores.core.exceptions.NotFoundException;
import me.gabu.gabazar.autores.core.model.Autor;

@ExtendWith(MockitoExtension.class)
class AutorDAOImplTest {

    private static final String AUTOR_ID = UUID.randomUUID().toString();

    private @Mock AutorRepository repository;
    private @Mock AutorEntityMapper mapper;
    private @InjectMocks @Spy AutorDAOImpl dao;

    private AutorEntity entity = new AutorEntity();
    private Autor model = new Autor();
    private static final String NOME = "nome";

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findById() {
        when(repository.findById(AUTOR_ID)).thenReturn(Optional.of(entity));
        when(dao.getMapper()).thenReturn(mapper);
        when(mapper.autorEntityToAutor(entity)).thenReturn(model);

        dao.findById(AUTOR_ID);

        verify(repository, times(1)).findById(AUTOR_ID);
    }

    @Test
    void findByIdNotFoundException() {
        when(repository.findById(AUTOR_ID)).thenReturn(Optional.empty());

        NotFoundException assertThrows = assertThrows(NotFoundException.class, () -> {
            dao.findById(AUTOR_ID);
        });

        assertEquals("Autor não encontrado", assertThrows.getMessage());

        verify(repository, times(1)).findById(AUTOR_ID);
    }
    

    @Test
    void save() {
        when(repository.save(entity)).thenReturn(entity);
        when(dao.getMapper()).thenReturn(mapper);
        when(mapper.autorEntityToAutor(entity)).thenReturn(model);
        when(mapper.autorToAutorEntity(model)).thenReturn(entity);

        dao.save(model);

        verify(repository, times(1)).save(entity);
    }

    @Test
    void listAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(entity));
        when(dao.getMapper()).thenReturn(mapper);
        when(mapper.autorEntityToAutor(Arrays.asList(entity))).thenReturn(Arrays.asList(model));

        dao.listAll();

        verify(repository, times(1)).findAll();
    }


    @Test
    void findByNome() {
        when(repository.findByNome(NOME)).thenReturn(Arrays.asList(entity));
        when(dao.getMapper()).thenReturn(mapper);
        when(mapper.autorEntityToAutor(Arrays.asList(entity))).thenReturn(Arrays.asList(model));

        dao.findByNome(NOME);

        verify(repository, times(1)).findByNome(NOME);
    }


    @Test
    void update() {
        when(repository.save(entity)).thenReturn(entity);
        when(dao.getMapper()).thenReturn(mapper);
        when(mapper.autorEntityToAutor(entity)).thenReturn(model);
        when(mapper.autorToAutorEntity(model)).thenReturn(entity);

        dao.update(model);

        verify(repository, times(1)).save(entity);
    }


}
