package com.springJWT.yop.ConfigSecurity.fiiltro;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;



public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private String claimName;

    private  String claimValue;

    public CustomAuthorizationManager(String claimName, String claimValue) {
        this.claimName = claimName;
        this.claimValue = claimValue;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();
        if (auth == null || !auth.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        DecodedJWT jwt = (DecodedJWT) auth.getCredentials();
        String claim = jwt.getClaim(claimName).asString();
        return new AuthorizationDecision(claim != null && claim.equals(claimValue));
    }
}

