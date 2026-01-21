package br.incode.biblioteca.modal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String isbn;

    @Column(nullable = true)
    private String titulo;

    @Column(nullable = true)
    private String autor;

    @ManyToOne
    @Column(nullable = false)
    private Genero genero;

    @Column(nullable = false)
    private String editora;

    private LocalDate dataPublicacao;

    private String idioma;

    private Integer paginas;

    private String descricao;

    @Column(nullable = false)
    private Integer copiasTotal;

    @Column(nullable = false)
    private Integer copiasDisponiveis;

    @Column(nullable = false)
    private BigDecimal preco;

    private String imagemCapaUrl;

    @Column(nullable = false)
    private Boolean ativo;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime editadoEm;

    @AssertTrue(message = "Copias Disponiveis não podem execeder o numero de copias totais")
    public Boolean copiasDisponiveisValidade(){
        if(copiasTotal == null || copiasDisponiveis == null){
            return true;
        }
        return copiasTotal <= copiasDisponiveis;
    }
}
