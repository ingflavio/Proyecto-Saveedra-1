package com.Interfaces.Proyecto1.Controller;

import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioDto;
import com.Interfaces.Proyecto1.Entites.Dtos.UsuarioLoginDto;
import com.Interfaces.Proyecto1.Entites.Usuario;
import com.Interfaces.Proyecto1.Repository.UsuarioRepository;
import com.Interfaces.Proyecto1.Service.impl.UsuarioServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody UsuarioDto usuarioDto){
        Optional<Usuario>usuario1 =usuarioRepository.findByUsername(usuarioDto.getUsername());
        if(usuario1.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.Register(Usuario.builder()
                    .username(usuarioDto.getUsername())
                    .correo(usuarioDto.getCorreo())
                    .password(usuarioDto.getPassword())
                    .build()));

        }
        else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Ya Registrado");
        }

    }

    @PostMapping("/Login")
    public ResponseEntity<?> Login(@RequestBody UsuarioLoginDto usuarioLoginDto) {

       return ResponseEntity.ok(usuarioService.Login(usuarioLoginDto));



    }
    @GetMapping("/perfil")
    public String perfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "Perfil del usuario: " + currentPrincipalName;
    }




}
