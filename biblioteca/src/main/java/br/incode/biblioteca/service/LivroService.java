package br.incode.biblioteca.service;

import java.util.List;

import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.payload.request.LivroRequestPesquisa;
import br.incode.biblioteca.payload.response.PageResponse;

public interface LivroService {

    LivroDTO criarLivro(LivroDTO livroDTO);

    List<LivroDTO> cirarVariosLivros(List<LivroDTO> livros);

    List<LivroDTO> listarLivros();

    LivroDTO atualizarLivro(Long livroId, LivroDTO livroDTO);

    void deletarLivro(Long livroId);

    void hardDeletarLivro(Long livroId);

    LivroDTO buscarLivroPorId(Long livroId);

    LivroDTO buscarLivroPorISBN(String isbn);

    PageResponse<LivroDTO> pesquisarLivroComFiltros(LivroRequestPesquisa requestPesquisa);

    long totalDeLivrosAtivos();

    long totalDeLivrosDisponiveis();
}
