package br.incode.biblioteca.service;

import java.util.List;

import br.incode.biblioteca.exception.GeneroException;

//import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
//import org.springframework.data.domain.Page;

import br.incode.biblioteca.payload.dto.GeneroDTO;

public interface GeneroService {
    GeneroDTO criarGenero(GeneroDTO genero);

    List<GeneroDTO> listarTodosGeneros();

    GeneroDTO buscarGeneroPorId(Long id) throws GeneroException;

    GeneroDTO atualizarGenero(Long id, GeneroDTO generoDTO) throws GeneroException;

    void deletarGenero(Long id) throws GeneroException;

    void hardDeletarGenero(Long id) throws GeneroException;

    List<GeneroDTO> listarTodosGenerosAtivosComSubGeneros();

    List<GeneroDTO> listarTopNivelGeneros();

    //Page<GeneroDTO> pesquisarGeneros(String TermoPesquisado, Pageable pageable);

    Long buscarTotalDeGeneros();

    Long buscarQuantidadeDeLivrosPorGenero(Long id);

    

}
