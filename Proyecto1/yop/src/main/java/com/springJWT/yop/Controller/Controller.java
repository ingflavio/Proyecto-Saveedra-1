package com.springJWT.yop.Controller;

import com.springJWT.yop.Controller.Dtos.AuthRequest;
import com.springJWT.yop.Controller.Dtos.AuthResponse;
import com.springJWT.yop.Persistens.Usuario;
import com.springJWT.yop.Service.impl.ServiceUsuarioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class Controller {


    @Autowired
    private ServiceUsuarioImpl usuarioimpl;
    @GetMapping("/long-in")
    public ResponseEntity<?> login(@RequestBody AuthRequest userLoginRequest){
        System.out.println("h");

        return new ResponseEntity<>(usuarioimpl.Login(userLoginRequest), HttpStatus.OK);

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Usuario userEntity){

        return new ResponseEntity<>(usuarioimpl.Guardar(userEntity),HttpStatus.OK);

    }

    @GetMapping("/hello")
    public String holamundo(){

        return "hola carajo";

    }


}
