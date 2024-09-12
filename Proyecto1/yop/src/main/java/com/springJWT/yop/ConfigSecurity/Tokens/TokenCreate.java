package com.springJWT.yop.ConfigSecurity.Tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenCreate {

    @Value("${my.key.private}")
    private String PrivateKey;

    @Value("${my.user.private}")
    private String UserCreate;




   public String CrearToken(Authentication authentication){

        Algorithm algorithm = Algorithm.HMAC256(PrivateKey);
       String Username= authentication.getPrincipal().toString();
       String Permisos=  authentication.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

       String Token= JWT.create()
               .withIssuer(UserCreate)
               .withSubject(Username)
               .withClaim("authorities",Permisos)
               .withIssuedAt(new Date())
               .withExpiresAt(new Date(System.currentTimeMillis()+3600000))
               .sign(algorithm);

       return Token;

   }

   public DecodedJWT ValidarToken(String token){
       Algorithm algorithm = Algorithm.HMAC256(PrivateKey);
       DecodedJWT decodedJWT;

        try{
             JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withIssuer(UserCreate)
                    .build();


           return decodedJWT = verifier.verify(token);

        } catch (JWTVerificationException exception){
            System.out.println(token+"token");
            throw new JWTVerificationException("Token No valido");

        }

   }

   public String ObtenerUsername(DecodedJWT decodedJWT){

       return decodedJWT.getSubject();

   }
    public Claim ObtenerClaims(DecodedJWT decodedJWT){

        return decodedJWT.getClaim("authorities");

    }


}
