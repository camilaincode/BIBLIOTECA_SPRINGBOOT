package br.incode.biblioteca.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDTO {

    private Long id;

    @NotBlank(message = "ISBN é obrigatorio")
    private String isbn;
    
    @NotBlank(message = "Titulo é obrigatorio")
    @Size(min = 1, max = 255, message = "Titulo deve ter entre 1 e 255 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é obrigatorio")
    @Size(min = 1, max = 255, message = "Autor deve ter entre 1 e 255 caracteres")
    private String autor;

    @Size(max = 2000, message = "Descricao deve ter no maximo 2000 caracteres")
    private String descricao;

    @NotNull(message = "Preco é obrigatorio")
    @DecimalMin(value = "0.0", message = "Preco deve ser no minimo acima de 0.0")
    @Digits(integer = 8, fraction = 2, message = "Preco deve ter no maximo 8 digitos e estar dividido em duas fracoes")
    private BigDecimal preco;

    @NotNull(message = "Genero é obrigatorio")
    private Long generoId;

    private String generoNome;

    private String generoCodigo;

    @Size(max = 100, message = "Editora deve ter no maximo 100 caracteres")
    private String editora;

    private LocalDate dataPublicacao;

    @Size(max = 20, message = "Idioma deve ter no maximo 20 caracteres")
    private String idioma;

    @Min(value = 1, message = "Paginas devem ser no minimo 1")
    @Max(value = 50000, message = "Paginas não devem exidir 50000")
    private Integer paginas;

    @Min(value = 0, message = "Quantidade de copias não podem ser abaixo de zero")
    @NotNull(message = "Quantidade de copias é obrigatorio")
    private Integer copiasTotal;

    @Min(value = 0, message = "Copias disponiveis não podem ser abaixo de zero")
    @NotNull(message = "Quantidade de copias disponiveis é obrigatorio")
    private Integer copiasDisponiveis;

    @Size(max = 500, message = "a url da imagem da capa deve ter no maximo 500 caracteres")
    private String ImagemCapaUrl;

    private Boolean emprestado;
    private Boolean reservado;

    private Boolean ativo;

    private LocalDateTime criadoEm;

    private LocalDateTime editadoEm;
}
