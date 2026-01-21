package br.incode.biblioteca.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> conteudo;
    private int numeroPagina;
    private int tamanhoPagina;
    private long elementosTotais;
    private int paginasTotais;
    private boolean ultima;
    private boolean primeira;
    private boolean vazia;
}
