package br.incode.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.incode.biblioteca.exception.GeneroException;
import br.incode.biblioteca.payload.dto.GeneroDTO;
import br.incode.biblioteca.payload.response.ApiResponse;
import br.incode.biblioteca.service.impl.GeneroServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroServiceImpl generoServiceImpl;

    @PostMapping("/")
    public ResponseEntity<GeneroDTO> adicionarGenero(@RequestBody GeneroDTO genero) {
        GeneroDTO generoCriado = generoServiceImpl.criarGenero(genero);
        return ResponseEntity.ok(generoCriado);
    }

    @GetMapping()
    public ResponseEntity<?> listarTodosGeneros() {
        List<GeneroDTO> generos = generoServiceImpl.listarTodosGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{generoId}")
    public ResponseEntity<?> buscarGeneroPeloId(@PathVariable("generoId") Long generoId) throws GeneroException {
        GeneroDTO genero = generoServiceImpl.buscarGeneroPorId(generoId);
        return ResponseEntity.ok(genero);
    }

    @PutMapping("/{generoId}")
    public ResponseEntity<?> atualizarGenero(@PathVariable("generoId") Long generoId,
            @RequestBody GeneroDTO generoEditado) throws GeneroException {
        GeneroDTO genero = generoServiceImpl.atualizarGenero(generoId, generoEditado);
        return ResponseEntity.ok(genero);
    }

    @DeleteMapping("/{generoId}")
    public ResponseEntity<?> desativarGenero(@PathVariable("generoId") Long generoId) throws GeneroException {
        generoServiceImpl.deletarGenero(generoId);
        ApiResponse res = new ApiResponse("genero desativado com sucesso!", true);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{generoId}/hard")
    public ResponseEntity<?> deletarGenero(@PathVariable("generoId") Long generoId) throws GeneroException {
        generoServiceImpl.hardDeletarGenero(generoId);
        ApiResponse res = new ApiResponse("genero deletado com sucesso!", true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/top-level")
    public ResponseEntity<?> listarTopLevelGeneros() {
        List<GeneroDTO> generos = generoServiceImpl.listarTopNivelGeneros();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> totalDeGenerosAtivos() {
        Long ativos = generoServiceImpl.buscarTotalDeGeneros();
        return ResponseEntity.ok(ativos);
    }

    @GetMapping("/{id}/total-livros")
    public ResponseEntity<?> totalDeGenerosPorLivro(@PathVariable Long id){
        Long livros = generoServiceImpl.buscarQuantidadeDeLivrosPorGenero(id);
        return ResponseEntity.ok(livros);
    }
}
