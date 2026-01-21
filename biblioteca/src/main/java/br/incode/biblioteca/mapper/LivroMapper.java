package br.incode.biblioteca.mapper;

import org.springframework.stereotype.Component;

import br.incode.biblioteca.exception.LivroException;
import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.modal.Livro;
import br.incode.biblioteca.payload.dto.LivroDTO;
import br.incode.biblioteca.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroMapper {
    private final GeneroRepository generoRepository;

    public LivroDTO emDto(Livro livro) {
        if (livro == null) {
            return null;
        }

        LivroDTO dto = LivroDTO.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .descricao(livro.getDescricao())
                .preco(livro.getPreco())
                .generoId(livro.getGenero().getId())
                .generoNome(livro.getGenero().getNomeGenero())
                .generoCodigo(livro.getGenero().getCodigo())
                .editora(livro.getEditora())
                .dataPublicacao(livro.getDataPublicacao())
                .idioma(livro.getIdioma())
                .paginas(livro.getPaginas())
                .copiasTotal(livro.getCopiasTotal())
                .copiasDisponiveis(livro.getCopiasDisponiveis())
                .ImagemCapaUrl(livro.getImagemCapaUrl())
                .ativo(livro.getAtivo())
                .criadoEm(livro.getCriadoEm())
                .editadoEm(livro.getEditadoEm())
                .build();
        return dto;
    }

    public Livro emEntidade(LivroDTO livroDTO) throws LivroException {
        if (livroDTO == null) {
            return null;
        }

        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setDescricao(livroDTO.getDescricao());

        if (livroDTO.getGeneroId() != null) {
            Genero genero = generoRepository.findById(livroDTO.getGeneroId())
                    .orElseThrow(
                            () -> new LivroException("Genero com id:" + livroDTO.getGeneroId() + "não encontrado!"));

            livro.setGenero(genero);
        }

        livro.setEditora(livroDTO.getEditora());
        livro.setIdioma(livroDTO.getIdioma());
        livro.setCopiasTotal(livroDTO.getCopiasTotal());
        livro.setCopiasDisponiveis(livroDTO.getCopiasDisponiveis());
        livro.setPreco(livroDTO.getPreco());
        livro.setPaginas(livroDTO.getPaginas());
        livro.setDataPublicacao(livroDTO.getDataPublicacao());
        livro.setImagemCapaUrl(livroDTO.getImagemCapaUrl());
        livro.setAtivo(livroDTO.getAtivo());
        livro.setCriadoEm(livroDTO.getCriadoEm());
        livro.setEditadoEm(livroDTO.getEditadoEm());

        return livro;
    }

    public void atualizarEntidadeporDTO(LivroDTO dto, Livro livro) throws LivroException {
        if (dto == null || livro == null) {
            return;
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setDescricao(dto.getDescricao());

        if (dto.getGeneroId() != null) {
            Genero genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(
                            () -> new LivroException("Genero com id:" + dto.getGeneroId() + "não encontrado!"));

            livro.setGenero(genero);
        }
        livro.setEditora(dto.getEditora());
        livro.setIdioma(dto.getIdioma());
        livro.setCopiasTotal(dto.getCopiasTotal());
        livro.setCopiasDisponiveis(dto.getCopiasDisponiveis());
        livro.setPreco(dto.getPreco());
        livro.setPaginas(dto.getPaginas());
        livro.setDataPublicacao(dto.getDataPublicacao());
        livro.setImagemCapaUrl(dto.getImagemCapaUrl());
        livro.setAtivo(dto.getAtivo());

        if(dto.getAtivo() != null){
            livro.setAtivo(dto.getAtivo());
        }

    }
}
