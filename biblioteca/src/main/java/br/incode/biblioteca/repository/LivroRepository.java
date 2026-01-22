package br.incode.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.incode.biblioteca.modal.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    @Query(" select l from Livro l where"
            + ":termoPesquisa is null OR"
            + "lower(l.titulo) like lower(concat('%',:termoPesquisa,'%')) OR"
            + "lower(l.autor) like lower(concat('%',:termoPesquisa,'%')) OR"
            + "lower(l.isbn) like lower(concat('%',:termoPesquisa,'%')) OR"
            + "(:generoId is null or l.genero.id=:generoId) AND"
            + ":disponiveisApenas == false Or l.copiasDisponiveis > 0) AND"
            + "l.ativo = true")
    Page<Livro> pesquisarLivroComFiltros(
            @Param("termoPesquisa") String termoPesquisa,
            @Param("generoId") Long generoId,
            @Param("disponiveisApenas") boolean disponivelApenas,
            Pageable pageable);

    long countByAtivoTrue();

    @Query("select count l from Livro l where l.copiasDisponiveis > 0 and l.ativo = true")
    long countLivrosDisponiveis();

}
