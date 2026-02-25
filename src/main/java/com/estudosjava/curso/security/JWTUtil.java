package com.estudosjava.curso.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String secret;
    private final long expiration;  // em milissegundos
    private final Algorithm algorithm;

    public JWTUtil(
            @Value("${jwt.secret: sua_chave_super_secreta_muito_longa_e_segura}") String secret,
            @Value("${jwt.expiration: 86400000}") long expiration  // default: 24h
    ) {
        this.secret = secret;
        this.expiration = expiration;
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuer("com.estudosjava.curso")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                // .withClaim("role", "USER")  // exemplo se quiser claims extras
                .sign(algorithm);
    }

    public String validateToken(String token) throws JWTVerificationException {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("com.estudosjava.curso")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();

        } catch (TokenExpiredException e) {
            throw e;
        } catch (JWTVerificationException e) {
            throw e;
        }
    }

//    public String getSubjectFromToken(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getSubject();
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
