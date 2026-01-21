package br.incode.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.incode.biblioteca.modal.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    
}
