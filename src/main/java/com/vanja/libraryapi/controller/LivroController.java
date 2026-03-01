package com.vanja.libraryapi.controller;

import com.vanja.libraryapi.controller.dto.CadastroLivroDTO;
import com.vanja.libraryapi.controller.dto.ErroResposta;
import com.vanja.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.vanja.libraryapi.controller.mappers.LivroMapper;
import com.vanja.libraryapi.exceptions.RegistroDuplicadoException;
import com.vanja.libraryapi.model.Livro;
import com.vanja.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {

        // mapear dto para entidade
        Livro livro = mapper.toEntity(dto);
        // enviar a entidade para o serviçe validar e salvar na base
        service.salvar(livro);
        // criar url para acesso dos dados do livro
        var url = gerarHeaderLocation(livro.getId());
        // retornar codigo created com header location
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(
            @PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
