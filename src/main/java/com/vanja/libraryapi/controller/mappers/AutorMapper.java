package com.vanja.libraryapi.controller.mappers;

import com.vanja.libraryapi.controller.dto.AutorDTO;
import com.vanja.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
