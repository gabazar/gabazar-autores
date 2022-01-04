package me.gabu.gabazar.autores.adapters.data.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutorEntityTest {

    private AutorEntity autor;

    @BeforeEach
    public void beforeEach() {
        autor = new AutorEntity();

        assertNull(autor.getId());
        assertNull(autor.getDataCriacao());
        assertNull(autor.getDataAlteracao());
    }

    @AfterEach
    public void afterAll() {
        autor.postRemoval();
    }

    @Test
    void prePersist() {
        autor.prePersist();
        assertNotNull(autor.getId());
        assertNotNull(autor.getDataCriacao());
        autor.postPersist();
    }

    @Test
    void preUpdate() {
        autor.preUpdate();
        assertNotNull(autor.getDataAlteracao());
        autor.postUpdate();
    }
}
