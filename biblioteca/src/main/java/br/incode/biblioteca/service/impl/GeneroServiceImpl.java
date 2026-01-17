package br.incode.biblioteca.service.impl;

import org.springframework.stereotype.Service;

import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.service.GeneroService;
import lombok.RequiredArgsConstructor;
import br.incode.biblioteca.repository.GeneroRepository;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository repository;
    
    @Override
    public Genero criarGenero(Genero genero){
        return repository.save(genero);
    }
    
}
