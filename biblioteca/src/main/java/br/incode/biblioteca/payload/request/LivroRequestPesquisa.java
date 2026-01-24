package br.incode.biblioteca.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestPesquisa {

    private String termoPesquisa;
    private Long generoId;
    private boolean apenasDisponiveis;
    private Integer pagina = 0;
    private Integer tamanho = 20;
    private String ordernarPor = "criadoEm";
    private String ordemDirecao = "DESC";
}
