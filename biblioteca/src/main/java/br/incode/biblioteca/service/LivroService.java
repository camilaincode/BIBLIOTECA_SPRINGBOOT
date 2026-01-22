package br.incode.biblioteca.service;

import java.util.List;

import br.incode.biblioteca.exception.LivroException;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.payload.request.LivroRequestPesquisa;
import br.incode.biblioteca.payload.response.PageResponse;

public interface LivroService {

    LivroDTO criarLivro(LivroDTO livroDTO) throws LivroException;

    List<LivroDTO> cirarVariosLivros(List<LivroDTO> livros) throws LivroException;

    List<LivroDTO> listarLivros();

    LivroDTO atualizarLivro(Long livroId, LivroDTO livroDTO) throws LivroException;

    void deletarLivro(Long livroId) throws LivroException;

    void hardDeletarLivro(Long livroId) throws LivroException;

    LivroDTO buscarLivroPorId(Long livroId) throws LivroException;

    LivroDTO buscarLivroPorISBN(String isbn) throws LivroException;

    PageResponse<LivroDTO> pesquisarLivroComFiltros(LivroRequestPesquisa requestPesquisa);

    long totalDeLivrosAtivos();

    long totalDeLivrosDisponiveis();
}
