package com.vanja.libraryapi.controller.dto;

import com.vanja.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * "isbn": "string",
 * "titulo": "string",
 * "dataPublicacao": "date",
 * "genero": "enum",
 * "preco": number,
 * "id_autor": "uuid"
 */

public record CadastroLivroDTO(
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        UUID idAutor
        ) {
}
