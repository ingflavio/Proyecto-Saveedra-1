package com.springJWT.yop.Controller;

import com.springJWT.yop.Controller.Dtos.AuthLoginRequest;
import com.springJWT.yop.Controller.Dtos.AuthRequest;

import com.springJWT.yop.Persistens.Permisos;
import com.springJWT.yop.Persistens.Usuario;
import com.springJWT.yop.Service.impl.ServiceUsuarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {


    @Autowired
    private ServiceUsuarioImpl usuarioimpl;
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest userLoginRequest){

        return new ResponseEntity<>(usuarioimpl.Login(userLoginRequest), HttpStatus.OK);

    }

    @PostMapping("/Register")
    public ResponseEntity<?> guardar(@RequestBody AuthRequest userEntity){

        Optional<Usuario> usuario1 =usuarioimpl.findbyName(userEntity.getUsername());
        if(usuario1.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioimpl.Guardar(Usuario.builder()
                    .username(userEntity.getUsername())
                    .correo(userEntity.getCorreo())
                    .password(userEntity.getPassword())
                    .permisos(Collections.singleton(Permisos.READ))
                    .build()));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Ya Registrado");
        }

    }



    @GetMapping("/hello")
    public String holamundo(){

        return "hola carajo";

    }


}
