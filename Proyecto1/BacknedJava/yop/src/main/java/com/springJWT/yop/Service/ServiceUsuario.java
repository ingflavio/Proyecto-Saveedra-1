package com.springJWT.yop.Service;

import com.springJWT.yop.Controller.Dtos.AuthLoginRequest;
import com.springJWT.yop.Controller.Dtos.AuthRequest;
import com.springJWT.yop.Controller.Dtos.AuthResponse;
import com.springJWT.yop.Persistens.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ServiceUsuario {


    Optional<Usuario> findbyName(String username);


    String Guardar(Usuario usuario);

    AuthResponse Login(AuthLoginRequest request);
    Authentication Auth(String username, String password);






}
