package br.incode.biblioteca.mapper;

import java.util.stream.Collectors;

import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.payload.dto.GeneroDTO;

public class GeneroMapper {
    public static GeneroDTO emDTO(Genero genero){
        if (genero == null){
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
        if(genero.getGeneroPai() != null){
            dto.setGeneroPaiId(genero.getGeneroPai().getId());
            dto.setGeneroPaiNome(genero.getGeneroPai().getNomeGenero());
        }

        dto.setSubGenero(genero.getSubGenero().stream()
                        .filter(subGenero -> subGenero.getAtivo())
                        .map(subGenero -> emDTO(subGenero)).collect(Collectors.toList()));

        return dto;
    }
}
