package com.springJWT.yop.Service.impl;

import com.springJWT.yop.ConfigSecurity.Tokens.TokenCreate;
import com.springJWT.yop.Controller.Dtos.AuthRequest;
import com.springJWT.yop.Controller.Dtos.AuthResponse;
import com.springJWT.yop.Persistens.Permisos;
import com.springJWT.yop.Persistens.Usuario;
import com.springJWT.yop.Repository.UsuarioRepository;
import com.springJWT.yop.Service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario, UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenCreate tokenCreate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> findbyName(String username) {
        return usuarioRepository.findByUsername(username);

    }

    @Override
    public Usuario Guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public AuthResponse Login(AuthRequest request) {

        String username=request.getUsername();
        String contra=request.getPassword();

        Authentication authentication = this.Auth(username, contra);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenCreate.CrearToken(authentication);
        return new AuthResponse(username,accessToken,"Bienvenido");

    }

    @Override
    public Authentication Auth(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);

        if(usuario.isPresent()){

            Set<Permisos> permisos = usuario.get().getPermisos();
            List<GrantedAuthority> authorities = permisos.stream()
                    .map(permiso -> new SimpleGrantedAuthority(permiso.name()))
                    .collect(Collectors.toList());

            System.out.println(authorities);

                return new User(usuario.get().getUsername(),usuario.get().getPassword(),authorities);
        }

    else {
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    }
}
