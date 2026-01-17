package br.incode.biblioteca.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Codigo do genero é obrigatorio")
    private String codigo;

    @NotBlank(message = "Nome do genero é obrigatorio")
    private String nomeGenero;

    @Size(max = 500, message = "descricao deve ter menos de 500 caracteres")
    private String descricao;

    @Min(value = 0, message = "ordem de display não pode ser menor que zero")
    private Integer ordemDisplay = 0;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    private Genero generoPai;

    @OneToMany
    private List<Genero> subGenero = new ArrayList<Genero>();

    //@OneToMany(mappedBy = "genero", cascade = CascadeType.PERSIST)
    //private List<Livro> livros = new ArrayList<Livro>();

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime editadoEm;
}
