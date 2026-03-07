package com.vanja.libraryapi.service;

import com.vanja.libraryapi.model.GeneroLivro;
import com.vanja.libraryapi.model.Livro;
import com.vanja.libraryapi.repository.LivroRepository;
import com.vanja.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    // isbn, titulo, nome autor, genero, ano de publicação
    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicao){

        // select * from livro where isbn = :isbn and nomeAutor =

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(genero))
//                ;

        // select * from livro where 0 = 0

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null){
            // query = query and isbn = :isbn
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null){
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(LivroSpecs.generoEqual(genero));
        }

//        Não mais usual depopis de criar o pacote e classe specs
        //        // where isbn =:isbn
//        Specification<Livro> isbnEqual = (root, query, cb ) ->
//                cb.equal(root.get("isbn"), isbn);

//        return repository.findAll(LivroSpecs.isbnEqual(isbn));

        return repository.findAll(specs);
    }
}
