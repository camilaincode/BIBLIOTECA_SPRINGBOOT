package br.incode.biblioteca.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.incode.biblioteca.config.JwtProvider;
import br.incode.biblioteca.domain.UserRole;
import br.incode.biblioteca.exception.UserException;
import br.incode.biblioteca.mapper.UserMapper;
import br.incode.biblioteca.modal.User;
import br.incode.biblioteca.payload.dto.UserDTO;
import br.incode.biblioteca.payload.response.AuthResponse;
import br.incode.biblioteca.repository.UserRespository;
import br.incode.biblioteca.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRespository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public AuthResponse login(String username, String senha) {
        Authentication authentication = authenticate(username, senha);

        return null;
    }

    private Authentication authenticate(String username, String senha){
        return null;
    }

    @Override
    public AuthResponse singup(UserDTO request) throws UserException {
        User user = repository.findByEmail(request.getEmail());

        if (user != null){
            throw new UserException("esse email já está sendo utilizado!");
        }

        User userCriado = new User();
        userCriado.setNomeCompleto(request.getNomeCompleto());
        userCriado.setEmail(request.getEmail());
        userCriado.setSenha(passwordEncoder.encode(request.getSenha()));
        userCriado.setTelefone(request.getTelefone());
        userCriado.setUltimoLogin(LocalDateTime.now());
        userCriado.setUsername(request.getUsername());
        userCriado.setPapel(UserRole.ROLE_USER);

        User userSalvo = repository.save(userCriado);

        Authentication auth = new UsernamePasswordAuthenticationToken(userSalvo.getEmail(),userSalvo.getSenha());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.tokenGerado(auth);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMensagem("pedido bem sucedido");
        response.setTitulo("Bem-vindo " + userSalvo.getNomeCompleto());
        response.setUserDTO(mapper.emDTO(userSalvo));

        return response;
    }

    @Override
    public void criarResetSenhaToken() {
    }

    @Override
    public void resetarSenha(String token, String novaSenha) {
    }

}
