package br.incode.biblioteca.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class GeneroDTO {
    private Long id;

    @NotBlank(message = "Codigo do genero é obrigatorio")
    private String codigo;

    @NotBlank(message = "Nome do genero é obrigatorio")
    private String nomeGenero;

    @Size(max = 500, message = "descricao deve ter menos de 500 caracteres")
    private String descricao;

    @Min(value = 0, message = "ordem de display não pode ser menor que zero")
    private Integer ordemDisplay;

    private Boolean ativo;

    private Long generoPaiId;

    private String generoPaiNome;

    private List<GeneroDTO> subGenero;

    private Long quantidadeDeLivros;
    
    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
