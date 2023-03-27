package com.stockweb.demo.ports.input.rs.api;


import com.stockweb.demo.ports.input.rs.response.authentication.AuthenticationRequest;
import com.stockweb.demo.ports.input.rs.response.authentication.AuthenticationResponse;
import com.stockweb.demo.ports.input.rs.response.authentication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Validated
public interface ApiAuth {

    ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request);

    AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request);

}
