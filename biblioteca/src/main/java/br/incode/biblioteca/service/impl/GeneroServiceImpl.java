package br.incode.biblioteca.service.impl;

import org.springframework.stereotype.Service;

import br.incode.biblioteca.mapper.GeneroMapper;
import br.incode.biblioteca.modal.Genero;
import br.incode.biblioteca.payload.dto.GeneroDTO;
import br.incode.biblioteca.service.GeneroService;
import lombok.RequiredArgsConstructor;
import br.incode.biblioteca.repository.GeneroRepository;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository repository;
    
    @Override
    public GeneroDTO criarGenero(GeneroDTO generoDTO){
        Genero genero = Genero.builder()
                                        .codigo(generoDTO.getCodigo())
                                        .nomeGenero(generoDTO.getNomeGenero())
                                        .descricao(generoDTO.getDescricao())
                                        .ativo(true)
                                        .ordemDisplay(generoDTO.getOrdemDisplay())
                                        .build();

        if(generoDTO.getGeneroPaiId() != null){
            Genero generoPai = repository.findById(generoDTO.getGeneroPaiId()).get();
            genero.setGeneroPai(generoPai);
        }

        Genero generoCriado = repository.save(genero);

        GeneroDTO generoEmDTO = GeneroMapper.emDTO(generoCriado);

        return generoEmDTO;
    }
    
}
