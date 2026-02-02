package br.incode.biblioteca.config;

import java.util.Collection;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String tokenGerado(Authentication authentication){
        Collection <? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
    }
}
