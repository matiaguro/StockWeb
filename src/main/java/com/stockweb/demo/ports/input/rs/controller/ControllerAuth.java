package com.stockweb.demo.ports.input.rs.controller;


import com.stockweb.demo.config.security.JwtService;
import com.stockweb.demo.core.model.Usuario;
import com.stockweb.demo.core.usecase.AuthService;
import com.stockweb.demo.ports.input.rs.api.ApiAuth;
import com.stockweb.demo.ports.input.rs.mapper.UsuarioControllerMapper;
import com.stockweb.demo.ports.input.rs.response.authentication.AuthenticationRequest;
import com.stockweb.demo.ports.input.rs.response.authentication.AuthenticationResponse;
import com.stockweb.demo.ports.input.rs.response.authentication.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

import static com.stockweb.demo.ports.input.rs.api.ApiConstants.AUTH_URI;

@RestController
@RequestMapping(AUTH_URI)
@RequiredArgsConstructor
public class ControllerAuth implements ApiAuth {

    private final UsuarioControllerMapper usuarioControllerMapper;
    private final AuthService authService;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        Usuario usuario = usuarioControllerMapper.registerRequestToUser(request);
        authService.createEntity(usuario);
        String jwt = jwtService.generateToken(usuario);
        Date expiration = jwtService.extractExpiration(jwt);
        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setToken(jwt);
        auth.setExpirationDate(expiration);
        return new ResponseEntity<>(auth, HttpStatus.CREATED);
    }



    @Override
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request) {
        return createJwtToken(request.getEmail(), request.getPassword());
    }

    public AuthenticationResponse createJwtToken(String username, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails user1) {
            String jwt = jwtService.generateToken(user1);
            Date expiration = jwtService.extractExpiration(jwt);
            return AuthenticationResponse.builder().token(jwt).expirationDate(expiration).build();
        }
        throw new AccessDeniedException("error in the authentication process");
    }



}
