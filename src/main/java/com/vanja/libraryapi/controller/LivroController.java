package com.vanja.libraryapi.controller;

import com.vanja.libraryapi.controller.dto.CadastroLivroDTO;
import com.vanja.libraryapi.controller.dto.ErroResposta;
import com.vanja.libraryapi.controller.mappers.LivroMapper;
import com.vanja.libraryapi.exceptions.RegistroDuplicadoException;
import com.vanja.libraryapi.model.Livro;
import com.vanja.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try {
            // mapear dto para entidade
            Livro livro = mapper.toEntity(dto);
            // enviar a entidade para o serviçe validar e salvar na base
            service.salvar(livro);
            // criar url para acesso dos dados do livro
            // retornar codigo created com header location
            return ResponseEntity.ok(livro);
        }catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
