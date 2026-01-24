package br.incode.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.incode.biblioteca.exception.LivroException;
import br.incode.biblioteca.mapper.LivroMapper;
import br.incode.biblioteca.modal.Livro;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.payload.request.LivroRequestPesquisa;
import br.incode.biblioteca.payload.response.PageResponse;
import br.incode.biblioteca.repository.LivroRepository;
import br.incode.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {
    private final LivroRepository repository;
    private final LivroMapper mapper;

    @Override
    public LivroDTO criarLivro(LivroDTO livroDTO) throws LivroException {
        if (repository.existsByIsbn(livroDTO.getIsbn())) {
            throw new LivroException("livro com isbn: " + livroDTO.getIsbn() + " já existe");
        }

        Livro livro = mapper.emEntidade(livroDTO);

        livro.copiasDisponiveisValidade();

        repository.save(livro);

        return mapper.emDto(livro);
    }

    @Override
    public List<LivroDTO> cirarVariosLivros(List<LivroDTO> livros) throws LivroException {
        List<LivroDTO> livrosSalvos = new ArrayList<>();

        for (LivroDTO livroDTO : livros) {
            criarLivro(livroDTO);
            livrosSalvos.add(livroDTO);
        }
        return livrosSalvos;
    }

    @Override
    public List<LivroDTO> listarLivros() {

        return repository.findAll().stream()
                          .map(livro -> mapper.emDto(livro))
                          .collect(Collectors.toList());
    }

    @Override
    public LivroDTO atualizarLivro(Long livroId, LivroDTO livroDTO) throws LivroException {
        Livro livro = repository.findById(livroId).orElseThrow(
                () -> new LivroException("Livro com id: " + livroId + "não encontrado!"));

        mapper.atualizarEntidadeporDTO(livroDTO, livro);

        livro.copiasDisponiveisValidade();

        Livro livroAtualizado = repository.save(livro);

        return mapper.emDto(livroAtualizado);
    }

    @Override
    public void deletarLivro(Long livroId) throws LivroException {
        Livro livro = repository.findById(livroId).orElseThrow(
                () -> new LivroException("Livro com id: " + livroId + "não encontrado!"));

        livro.setAtivo(false);

        repository.save(livro);
    }

    @Override
    public void hardDeletarLivro(Long livroId) throws LivroException {
        Livro livro = repository.findById(livroId).orElseThrow(
                () -> new LivroException("Livro com id: " + livroId + "não encontrado!"));
        repository.delete(livro);
    }

    @Override
    public LivroDTO buscarLivroPorId(Long livroId) throws LivroException {
        Livro livro = repository.findById(livroId).orElseThrow(
                () -> new LivroException("Livro com id: " + livroId + "não encontrado!"));

        return mapper.emDto(livro);
    }

    @Override
    public LivroDTO buscarLivroPorISBN(String isbn) throws LivroException {
        Livro livro = repository.findByIsbn(isbn).orElseThrow(
                () -> new LivroException("Livro com a ISB: " + isbn + "não foi encontrado!"));

        return mapper.emDto(livro);
    }

    @Override
    public PageResponse<LivroDTO> pesquisarLivroComFiltros(LivroRequestPesquisa requestPesquisa) {
        Pageable pageable = criarPageable(requestPesquisa.getPagina(), requestPesquisa.getTamanho(),
                requestPesquisa.getOrdernarPor(), requestPesquisa.getOrdemDirecao());
        Page<Livro> livroPagina = repository.pesquisarLivroComFiltros(
                requestPesquisa.getTermoPesquisa(),
                requestPesquisa.getGeneroId(),
                requestPesquisa.isApenasDisponiveis(),
                pageable);
        return coneverterPageResponseDTO(livroPagina);
    }

    @Override
    public long totalDeLivrosAtivos() {

        return repository.countByAtivoTrue();
    }

    @Override
    public long totalDeLivrosDisponiveis() {

        return repository.countLivrosDisponiveis();
    }

    private Pageable criarPageable(int pagina, int tamanho, String ordemPor, String ordemDirecao) {
        tamanho = Math.min(tamanho, 10);
        tamanho = Math.max(tamanho, 1);
        Sort ordem = ordemDirecao.equalsIgnoreCase("ASC") ? Sort.by(ordemPor).ascending()
                : Sort.by(ordemPor).descending();
        return PageRequest.of(pagina, tamanho, ordem);
    }

    private PageResponse<LivroDTO> coneverterPageResponseDTO(Page<Livro> page) {
        List<LivroDTO> livrosDTO = page.getContent().stream()
                .map(mapper::emDto)
                .collect(Collectors.toList());

        return new PageResponse<>(livrosDTO, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages(),
                page.isLast(), page.isFirst(), page.isEmpty());
    }
}
