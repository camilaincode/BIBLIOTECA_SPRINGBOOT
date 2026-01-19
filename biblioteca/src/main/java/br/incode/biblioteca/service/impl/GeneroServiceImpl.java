package br.incode.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.incode.biblioteca.exception.GeneroException;
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
    public GeneroDTO buscarGeneroPorId(Long id) throws GeneroException{
        Genero genero = repository.findById(id).orElseThrow(
            () -> new GeneroException("genero não encontrado!")
        );
        return mapper.emDTO(genero);
    }

    @Override
    public GeneroDTO atualizarGenero(Long id, GeneroDTO generoDTO) throws GeneroException{
        Genero generoParaAtualizar = repository.findById(id).orElseThrow(
            () -> new GeneroException("genero não encontrado!")
        );

        mapper.atualizarEntidadeporDto(generoDTO, generoParaAtualizar);

        Genero generoAtualizado = repository.save(generoParaAtualizar);

        return mapper.emDTO(generoAtualizado);
    }

    @Override
    public void deletarGenero(Long id) throws GeneroException{
        Genero genero = repository.findById(id).orElseThrow(
            () -> new GeneroException("genero não encontrado!")
        );

        genero.setAtivo(false);
        repository.save(genero);
    }

    @Override
    public void hardDeletarGenero(Long id) throws GeneroException{
        Genero genero = repository.findById(id).orElseThrow(
            () -> new GeneroException("genero não encontrado!")
        );
        repository.delete(genero);
    }

    @Override
    public List<GeneroDTO> listarTodosGenerosAtivosComSubGeneros(){
        List<Genero> topLevelGeneros = repository.findByGeneroPaiIsNullAndAtivoTrueOrderByOrdemDisplayAsc();
        return mapper.emListaDtos(topLevelGeneros);
    }

    @Override
    public List<GeneroDTO> listarTopNivelGeneros(){
        List<Genero> topLevelGeneros = repository.findByGeneroPaiIsNullAndAtivoTrueOrderByOrdemDisplayAsc();
        return mapper.emListaDtos(topLevelGeneros);
    }

    @Override
    public Long buscarTotalDeGeneros(){
        return repository.countByAtivoTrue();
    }

    @Override
    public Long buscarQuantidadeDeLivrosPorGenero(Long id){
        return null;
    }
    
}
