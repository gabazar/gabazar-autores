package me.gabu.gabazar.autores.adapters.data.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.gabu.gabazar.autores.adapters.data.entity.AutorEntity;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, String> {

    Collection<AutorEntity> findByNome(String nome);
}
