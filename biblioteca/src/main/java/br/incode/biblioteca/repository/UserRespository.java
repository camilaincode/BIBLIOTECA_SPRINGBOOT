package br.incode.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.incode.biblioteca.modal.User;


public interface UserRespository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
