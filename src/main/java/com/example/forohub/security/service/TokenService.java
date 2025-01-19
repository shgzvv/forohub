package com.example.forohub.security.service;

import com.auth0.jwt.JWT;
import com.example.forohub.models.dto.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generateExpirationDate(5))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al crear el token");
        }

    }

    public String getSubject (String token) {
        if (token == null) throw new RuntimeException();
        DecodedJWT verifier = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm).withIssuer("forohub").build().verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error al verificar el token. " + e.toString());
        }
        if (verifier.getSubject() == null) throw new RuntimeException("Error al verificar el token");
        return verifier.getSubject();
    }

    private Instant generateExpirationDate(int hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-05:00"));
    }
}
