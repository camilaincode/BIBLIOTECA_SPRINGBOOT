package br.incode.biblioteca.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.incode.biblioteca.modal.User;
import br.incode.biblioteca.repository.UserRespository;

@Service
public class CustomUserServiceImplementation implements UserDetailsService{

    private final UserRespository respository;

    public CustomUserServiceImplementation(UserRespository respository){
        this.respository = respository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = respository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuario com email: " + username + " não foi encontrado!");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getPapel().toString());
        Collection<? extends GrantedAuthority>authorities = Collections.singletonList(authority);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getSenha(), authorities);
    }
    
}
