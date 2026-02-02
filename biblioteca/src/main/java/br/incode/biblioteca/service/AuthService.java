package br.incode.biblioteca.service;

import br.incode.biblioteca.exception.UserException;
import br.incode.biblioteca.payload.dto.UserDTO;
import br.incode.biblioteca.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String senha);

    AuthResponse singup(UserDTO request) throws UserException;

    void criarResetSenhaToken();

    void resetarSenha(String token, String novaSenha);

}
