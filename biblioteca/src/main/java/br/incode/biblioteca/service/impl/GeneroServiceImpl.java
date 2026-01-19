package br.incode.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
    private final GeneroMapper mapper;
    
    @Override
    public GeneroDTO criarGenero(GeneroDTO generoDTO){
        Genero genero = mapper.emEntidade(generoDTO);
        Genero generoCriado = repository.save(genero);
        return mapper.emDTO(generoCriado);
    }

    @Override
    public List<GeneroDTO> listarTodosGeneros(){
        return repository.findAll().stream()
                .map(genero -> mapper.emDTO(genero))
                .collect(Collectors.toList());
    }

    @Override
    public GeneroDTO buscarGeneroPorId(Long id){
        return null;
    }

    @Override
    public GeneroDTO atualizarGenero(Long id, GeneroDTO generoDTO){
        return null;
    }

    @Override
    public void deletarGenero(Long id){
        
    }

    @Override
    public void hardDeletarGenero(Long id){

    }

    @Override
    public List<GeneroDTO> listarTodosGenerosAtivosComSubGeneros(){
        return null;
    }

    @Override
    public List<GeneroDTO> listarTopNivelGeneros(){
        return null;
    }

    @Override
    public Long buscarTotalDeGeneros(){
        return null;
    }

    @Override
    public Long buscarQuantidadeDeLivrosPorGenero(Long id){
        return null;
    }
    
}
