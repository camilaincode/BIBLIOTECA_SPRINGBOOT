package br.incode.biblioteca.payload.dto;

import java.time.LocalDateTime;

import br.incode.biblioteca.domain.UserRole;
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
public class UserDTO {
    private long id;
    private String email;
    private String senha;
    private String telefone;
    private String nomeCompleto;
    private UserRole papel;
    private String username;
    private LocalDateTime ultimoLogin;
}
