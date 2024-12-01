package com.springJWT.yop.Controller.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String username;
    private String Token;
    private String Mensaje;

}
