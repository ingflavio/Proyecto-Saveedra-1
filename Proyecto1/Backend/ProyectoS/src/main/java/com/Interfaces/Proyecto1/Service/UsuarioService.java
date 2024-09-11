package com.Interfaces.Proyecto1.Service;

import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioDto;
import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioLoginDto;
import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioResponse;
import com.Interfaces.Proyecto1.Entites.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    String Register(Usuario usuario);

    UsuarioResponse Login(UsuarioLoginDto usuarioDto);

    Usuario ActulizarUsuario(Usuario usuario);


}
