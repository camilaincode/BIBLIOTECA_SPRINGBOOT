package br.incode.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.incode.biblioteca.exception.LivroException;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.payload.request.LivroRequestPesquisa;
import br.incode.biblioteca.payload.response.ApiResponse;
import br.incode.biblioteca.payload.response.PageResponse;
import br.incode.biblioteca.service.impl.LivroServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<LivroDTO> criarLivro(@Valid @RequestBody LivroDTO livro) throws LivroException {
        LivroDTO livroCriado = service.criarLivro(livro);
        return ResponseEntity.ok(livroCriado);
    }

    @PostMapping("/varios")
    public ResponseEntity<?> criarVariosLivros(@Valid @RequestBody List<LivroDTO> livro) throws LivroException {
        List<LivroDTO> livroCriado = service.cirarVariosLivros(livro);
        return ResponseEntity.ok(livroCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) throws LivroException {
        LivroDTO livro = service.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDTO> buscarLivroPorIsbn(@PathVariable String isbn) throws LivroException {
        LivroDTO livro = service.buscarLivroPorISBN(isbn);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/")
    public ResponseEntity<PageResponse<LivroDTO>> pesquisarLivros(
            @RequestParam(required = false) Long generoId,
            @RequestParam(required = false, defaultValue = "false") boolean disponiveisApenas,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanho,
            @RequestParam(defaultValue = "criadoEm") String ordernarPor,
            @RequestParam(defaultValue = "DESC") String ordemDirecao) {
        LivroRequestPesquisa pedidopesquisa = new LivroRequestPesquisa();
        pedidopesquisa.setGeneroId(generoId);
        pedidopesquisa.setApenasDisponiveis(disponiveisApenas);
        pedidopesquisa.setPagina(pagina);
        pedidopesquisa.setTamanho(tamanho);
        pedidopesquisa.setOrdernarPor(ordernarPor);
        pedidopesquisa.setOrdemDirecao(ordemDirecao);

        PageResponse<LivroDTO> livros = service.pesquisarLivroComFiltros(pedidopesquisa);
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable long id, @Valid @RequestBody LivroDTO livro)
            throws LivroException {
        LivroDTO livroAtualizado = service.atualizarLivro(id, livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletarLivro(@PathVariable long id) throws LivroException {
        service.deletarLivro(id);
        return ResponseEntity.ok(new ApiResponse("livro deletado com sucesso", true));
    }

    @DeleteMapping("/{id}/permanente")
    public ResponseEntity<ApiResponse> hardDeletarLivro(@PathVariable long id) throws LivroException {
        service.hardDeletarLivro(id);
        return ResponseEntity.ok(new ApiResponse("livro deletado permanentemente com sucesso", true));
    }

    @PostMapping("/pesquisa")
    public ResponseEntity<PageResponse<LivroDTO>> pesquisaAvancada(@RequestBody LivroRequestPesquisa pesquisaRequest) {
        PageResponse<LivroDTO> livros = service.pesquisarLivroComFiltros(pesquisaRequest);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<LivroStatsResponse> getEstatisticasLivros() {
        long totalAtivo = service.totalDeLivrosAtivos();
        long totalDisponiveis = service.totalDeLivrosDisponiveis();

        LivroStatsResponse estatisticas = new LivroStatsResponse(totalAtivo, totalDisponiveis);
        return ResponseEntity.ok(estatisticas);
    }

    public static class LivroStatsResponse {
        public long totalLivrosAtivo;
        public long totalLivrosDisponiveis;

        public LivroStatsResponse(long totalLivrosAtivo, long totalLivrosDisponiveis) {
            this.totalLivrosAtivo = totalLivrosAtivo;
            this.totalLivrosDisponiveis = totalLivrosDisponiveis;
        }
    }

}
