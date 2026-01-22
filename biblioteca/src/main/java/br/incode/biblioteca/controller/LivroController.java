package br.incode.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.incode.biblioteca.exception.LivroException;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.service.impl.LivroServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroServiceImpl service;

    @PostMapping("/")
    public ResponseEntity<LivroDTO> criarLivro(@Valid @RequestBody LivroDTO livro) throws LivroException{
        LivroDTO livroCriado = service.criarLivro(livro);
        return ResponseEntity.ok(livroCriado);
    }

    @PostMapping("/varios")
    public ResponseEntity<?> criarVariosLivros(@Valid @RequestBody List<LivroDTO> livro) throws LivroException{
        List<LivroDTO> livroCriado = service.cirarVariosLivros(livro);
        return ResponseEntity.ok(livroCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long livroId) throws LivroException{
        LivroDTO livro = service.buscarLivroPorId(livroId);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDTO> buscarLivroPorIsbn(@PathVariable String isbn) throws LivroException{
        LivroDTO livro = service.buscarLivroPorISBN(isbn);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable long livroId,@Valid @RequestBody LivroDTO livro){
        try {
            LivroDTO livroAtualizado = service.atualizarLivro(livroId, livro);
            return ResponseEntity.ok(livroAtualizado);
        } catch (LivroException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
}
