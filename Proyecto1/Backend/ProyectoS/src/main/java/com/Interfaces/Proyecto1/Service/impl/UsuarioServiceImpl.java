package com.Interfaces.Proyecto1.Service.impl;

import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioDto;
import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioLoginDto;
import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioResponse;
import com.Interfaces.Proyecto1.Entites.Usuario;
import com.Interfaces.Proyecto1.Repository.UsuarioRepository;
import com.Interfaces.Proyecto1.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String Register(Usuario usuario) {
       usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        System.out.println(usuario.getCorreo());
        usuarioRepository.save(usuario);
        return "Usuario Registrado Con Exito";
    }

    @Override
    public UsuarioResponse Login(UsuarioLoginDto usuarioDto) {
        String username = usuarioDto.getUsername();
        String password = usuarioDto.getPassword();

        Authentication authentication = this.Auth(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new UsuarioResponse(username, "Bienvenido");
    }


    public Authentication Auth(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }




    @Override
    public Usuario ActulizarUsuario(Usuario usuario) {

        return null;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);

        if (usuario.isPresent()) {
            return new User(usuario.get().getUsername(), usuario.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
