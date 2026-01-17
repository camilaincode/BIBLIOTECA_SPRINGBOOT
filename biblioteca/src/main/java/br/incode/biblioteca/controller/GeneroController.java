package br.incode.biblioteca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.service.impl.GeneroServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroServiceImpl generoServiceImpl;

    @PostMapping("/criar")
    public ResponseEntity<Genero> adicionarGenero(@RequestBody Genero genero){
        Genero generoCriado = generoServiceImpl.criarGenero(genero);
        return ResponseEntity.ok(generoCriado);
    }
    
}
