package br.incode.biblioteca.payload.response;

import br.incode.biblioteca.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String mensagem;
    private String titulo;
    private UserDTO userDTO;
}
