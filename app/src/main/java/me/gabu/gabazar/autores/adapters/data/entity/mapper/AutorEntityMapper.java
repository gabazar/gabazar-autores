package me.gabu.gabazar.autores.adapters.data.entity.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import me.gabu.gabazar.autores.adapters.data.entity.AutorEntity;
import me.gabu.gabazar.autores.core.model.Autor;

@Mapper
public interface AutorEntityMapper {

    AutorEntityMapper INSTANCE = Mappers.getMapper(AutorEntityMapper.class);

    AutorEntity autorToAutorEntity(Autor autor);

    Autor autorEntityToAutor(AutorEntity autorEntity);

    Collection<AutorEntity> autorToAutorEntity(Collection<Autor> autor);

    Collection<Autor> autorEntityToAutor(Collection<AutorEntity> autorEntity);

}
