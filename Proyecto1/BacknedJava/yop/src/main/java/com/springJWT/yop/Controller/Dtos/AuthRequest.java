package com.springJWT.yop.Controller.Dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"username","correo","password"})
public class AuthRequest {

    private String username;
    private String correo;
    private String password;



}
