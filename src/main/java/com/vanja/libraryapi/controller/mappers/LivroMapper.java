package com.vanja.libraryapi.controller.mappers;

import com.vanja.libraryapi.controller.dto.CadastroLivroDTO;
import com.vanja.libraryapi.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "autor", source = "idAutor")
    Livro toEntity(CadastroLivroDTO dto);
}
