package com.projects.crm.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@Service
public class TokenService {
    private final static String serverSecret = "admin-service";
    private final static long EXPIRATION_TIME = 864_000_000; //10days
    
    private static Algorithm algorithm = null;
    private static JWTVerifier verifier = null;

    static{
        algorithm = Algorithm.HMAC256(serverSecret.getBytes());
        verifier = JWT.require(algorithm).build();
    }

    public String generateToken(Long id)throws JWTCreationException,Exception{
        return JWT.create()
            .withIssuer("crm-admin")
            .withClaim("ID",id)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(algorithm);
    }

    public String extractID(String token)throws JWTVerificationException,TokenExpiredException,AlgorithmMismatchException{
        return verifier.verify(token)
            .getClaim("ID")
            .toString();
    }

    public void verifyToken(String token)throws JWTVerificationException,TokenExpiredException{
        verifier.verify(token);
    }
    
}
