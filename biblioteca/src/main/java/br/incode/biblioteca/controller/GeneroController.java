package br.incode.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.incode.biblioteca.payload.dto.GeneroDTO;
import br.incode.biblioteca.service.impl.GeneroServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroServiceImpl generoServiceImpl;

    @PostMapping("/criar")
    public ResponseEntity<GeneroDTO> adicionarGenero(@RequestBody GeneroDTO genero){
        GeneroDTO generoCriado = generoServiceImpl.criarGenero(genero);
        return ResponseEntity.ok(generoCriado);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodosGeneros() {
        List<GeneroDTO> generos = generoServiceImpl.listarTodosGeneros();
        return ResponseEntity.ok(generos);
    }
    
    
}
