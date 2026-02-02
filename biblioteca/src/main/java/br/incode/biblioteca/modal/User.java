package br.incode.biblioteca.modal;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.incode.biblioteca.domain.AuthProvider;
import br.incode.biblioteca.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String nomeCompleto;

    @Column(unique = true)
    private String username;

    private UserRole papel;

    private String telefone;

    private AuthProvider authProvider = AuthProvider.LOCAL;

    private String googleId;

    private String imagemPerfilUrl;

    private String senha;

    private LocalDateTime ultimoLogin;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime editadoEm;
}
