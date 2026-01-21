package br.incode.biblioteca.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.incode.biblioteca.mapper.LivroMapper;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.payload.request.LivroRequestPesquisa;
import br.incode.biblioteca.payload.response.PageResponse;
import br.incode.biblioteca.repository.LivroRepository;
import br.incode.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {
    LivroRepository repository;
    LivroMapper mapper;

    @Override
    public LivroDTO criarLivro(LivroDTO livroDTO) {
        
        throw new UnsupportedOperationException("Unimplemented method 'criarLivro'");
    }
    @Override
    public List<LivroDTO> cirarVariosLivros(List<LivroDTO> livros) {
        
        throw new UnsupportedOperationException("Unimplemented method 'cirarVariosLivros'");
    }
    @Override
    public List<LivroDTO> listarLivros() {
        
        throw new UnsupportedOperationException("Unimplemented method 'listarLivros'");
    }
    @Override
    public LivroDTO atualizarLivro(Long livroId, LivroDTO livroDTO) {
        
        throw new UnsupportedOperationException("Unimplemented method 'atualizarLivro'");
    }
    @Override
    public void deletarLivro(Long livroId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deletarLivro'");
    }
    @Override
    public void hardDeletarLivro(Long livroId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'hardDeletarLivro'");
    }
    @Override
    public LivroDTO buscarLivroPorId(Long livroId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'buscarLivroPorId'");
    }
    @Override
    public LivroDTO buscarLivroPorISBN(String isbn) {
        
        throw new UnsupportedOperationException("Unimplemented method 'buscarLivroPorISBN'");
    }
    @Override
    public PageResponse<LivroDTO> pesquisarLivroComFiltros(LivroRequestPesquisa requestPesquisa) {
        
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarLivroComFiltros'");
    }
    @Override
    public long totalDeLivrosAtivos() {
        
        throw new UnsupportedOperationException("Unimplemented method 'totalDeLivrosAtivos'");
    }
    @Override
    public long totalDeLivrosDisponiveis() {
        
        throw new UnsupportedOperationException("Unimplemented method 'totalDeLivrosDisponiveis'");
    }
}
