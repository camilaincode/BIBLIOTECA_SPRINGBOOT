package br.incode.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.incode.biblioteca.modal.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long>{
    
    List<Genero> findByAtivoTrueOrderByDisplayOrderAsc();
    List<Genero> findByGeneroPaiIsNullAndAtivoTrueOrderByDisplayOrderAsc();
    List<Genero> findByGeneroPaiIdAndAtivoTrueOrderByDisplayOrderAsc(Long generoPaiId);
    long countByAtivoTrue();
    //@Query("select count(l) from livro l where l.genero.id=:generoId")
    //long countLivrosByGenero(@Param("generoId") Long generoId);
    
}
