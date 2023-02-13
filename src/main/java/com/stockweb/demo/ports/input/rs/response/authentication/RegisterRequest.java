package com.stockweb.demo.ports.input.rs.response.authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @JoinColumn(name = "firstname")
    private String firstname;

    @JoinColumn(name = "lastname")
    private String lastname;

    @JoinColumn(name = "email")
    private String email;

    @JoinColumn(name = "password")
    private String password;

}
