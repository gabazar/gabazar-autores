package me.gabu.gabazar.autores.adapters.html.in.dto.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import me.gabu.gabazar.autores.adapters.html.in.dto.AutorDTO;
import me.gabu.gabazar.autores.core.model.Autor;

@Mapper
public interface AutorDTOMapper {

    AutorDTOMapper INSTANCE = Mappers.getMapper(AutorDTOMapper.class);

    AutorDTO autorToAutorDto(Autor autor);

    Autor autorDtoToAutor(AutorDTO autordto);

    Collection<AutorDTO> autorToAutorDto(Collection<Autor> autor);

    Collection<Autor> autorDtoToAutor(Collection<AutorDTO> autordto);

}
