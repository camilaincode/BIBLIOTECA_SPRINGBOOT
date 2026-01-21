package br.incode.biblioteca.service;

import java.util.List;

import br.incode.biblioteca.payload.dto.LivroDTO;

public interface LivroService {

    LivroDTO criarLivro(LivroDTO livroDTO);

    List<LivroDTO> cirarVariosLivros(List<LivroDTO> livros);

    List<LivroDTO> listarLivros();

    LivroDTO atualizarLivro(Long livroId, LivroDTO livroDTO);

    void deletarLivro(Long livroId);

    void hardDeletarLivro(Long livroId);

    LivroDTO buscarLivroPorId(Long livroId);

    LivroDTO buscarLivroPorISBN(String isbn);

}
