package br.incode.biblioteca.mapper;

import org.springframework.stereotype.Component;

import br.incode.biblioteca.modal.User;
import br.incode.biblioteca.payload.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO emDTO (User user){
        if(user == null){
            return null;
        }

        UserDTO dto = UserDTO.builder()
                        .id(user.getId())
                        .nomeCompleto(user.getNomeCompleto())
                        .email(user.getEmail())
                        .senha(user.getSenha())
                        .telefone(user.getTelefone())
                        .username(user.getUsername())
                        .papel(user.getPapel())
                        .ultimoLogin(user.getUltimoLogin())
                        .build();
        return dto;
    }

    public User emEntidade(UserDTO dto){
        if(dto == null){
            return null;
        }

        User user = User.builder()
                    .id(dto.getId())
                    .nomeCompleto(dto.getNomeCompleto())
                    .build();

        return user;
    }
}
