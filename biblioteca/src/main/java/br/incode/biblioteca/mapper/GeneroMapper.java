package br.incode.biblioteca.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.payload.dto.GeneroDTO;
import br.incode.biblioteca.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class GeneroMapper {
    private final GeneroRepository repository;

    public GeneroDTO emDTO(Genero genero) {
        if (genero == null) {
            return null;
        }

        GeneroDTO dto = GeneroDTO.builder()
                .id(genero.getId())
                .codigo(genero.getCodigo())
                .nomeGenero(genero.getNomeGenero())
                .descricao(genero.getDescricao())
                .ordemDisplay(genero.getOrdemDisplay())
                .ativo(genero.getAtivo())
                .criadoEm(genero.getCriadoEm())
                .atualizadoEm(genero.getEditadoEm())
                .build();
        if (genero.getGeneroPai() != null) {
            dto.setGeneroPaiId(genero.getGeneroPai().getId());
            dto.setGeneroPaiNome(genero.getGeneroPai().getNomeGenero());
        }

        if (genero.getSubGenero() != null && !genero.getSubGenero().isEmpty()) {
            dto.setSubGenero(genero.getSubGenero().stream()
                    .filter(subGenero -> subGenero.getAtivo())
                    .map(subGenero -> emDTO(subGenero)).collect(Collectors.toList()));
        }

        return dto;
    }

    public Genero emEntidade(GeneroDTO generoDTO) {
        if (generoDTO == null) {
            return null;
        }

        Genero genero = Genero.builder()
                .codigo(generoDTO.getCodigo())
                .nomeGenero(generoDTO.getNomeGenero())
                .descricao(generoDTO.getDescricao())
                .ativo(true)
                .ordemDisplay(generoDTO.getOrdemDisplay())
                .build();

        if (generoDTO.getGeneroPaiId() != null) {
            Genero generoPai = repository.findById(generoDTO.getGeneroPaiId()).get();
            genero.setGeneroPai(generoPai);
        }

        return genero;
    }

    public void atualizarEntidadeporDto(GeneroDTO dto, Genero genero){
        if (dto == null || genero == null) {
            return;
        }

        genero.setCodigo(dto.getCodigo());
        genero.setNomeGenero(dto.getNomeGenero());
        genero.setDescricao(dto.getDescricao());
        genero.setOrdemDisplay(dto.getOrdemDisplay() != null ? dto.getOrdemDisplay() : 0);
        if(dto.getAtivo()!=null){
            genero.setAtivo(dto.getAtivo());
        }
        if(dto.getGeneroPaiId()!=null){
            repository.findById(dto.getGeneroPaiId()).ifPresent(genero :: setGeneroPai);
        }
    }

    public List<GeneroDTO> emListaDtos(List<Genero> listaGeneros){
        return listaGeneros.stream().map(genero -> emDTO(genero)).collect(Collectors.toList());
    }
}
