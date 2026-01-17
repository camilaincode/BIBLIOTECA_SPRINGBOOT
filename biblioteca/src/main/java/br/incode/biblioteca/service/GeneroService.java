package br.incode.biblioteca.service;

import java.util.List;

import br.incode.biblioteca.payload.dto.GeneroDTO;

public interface GeneroService {
    GeneroDTO criarGenero(GeneroDTO genero);

    List<GeneroDTO> listarTodosGeneros();

}
