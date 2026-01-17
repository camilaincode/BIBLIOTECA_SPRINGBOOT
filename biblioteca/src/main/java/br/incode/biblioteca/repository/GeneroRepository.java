package br.incode.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.incode.biblioteca.modal.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long>{
    
    
}
