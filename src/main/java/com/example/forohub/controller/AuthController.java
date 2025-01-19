package com.example.forohub.controller;

import com.example.forohub.models.dto.Usuario;
import com.example.forohub.models.dto.token.DTO_JWT_tkn;
import com.example.forohub.models.dto.user.DTO_UserAuth;
import com.example.forohub.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser (
            @RequestBody
            @Valid
            DTO_UserAuth userAuthDTO
    ) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userAuthDTO.username(),
                userAuthDTO.password()
        );
        var user = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((Usuario) user.getPrincipal());
        return ResponseEntity.ok(new DTO_JWT_tkn(token));
    }
}
