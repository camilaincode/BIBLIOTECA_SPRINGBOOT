package br.incode.biblioteca.service;

import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import br.incode.biblioteca.payload.dto.GeneroDTO;

public interface GeneroService {
    GeneroDTO criarGenero(GeneroDTO genero);

    List<GeneroDTO> listarTodosGeneros();

    GeneroDTO buscarGeneroPorId(Long id);

    GeneroDTO atualizarGenero(Long id, GeneroDTO generoDTO);

    void deletarGenero(Long id);

    void hardDeletarGenero(Long id);

    List<GeneroDTO> listarTodosGenerosAtivosComSubGeneros();

    List<GeneroDTO> listarTopNivelGeneros();

    //Page<GeneroDTO> pesquisarGeneros(String TermoPesquisado, Pageable pageable);

    Long buscarTotalDeGeneros();

    Long buscarQuantidadeDeLivrosPorGenero(Long id);

    

}
