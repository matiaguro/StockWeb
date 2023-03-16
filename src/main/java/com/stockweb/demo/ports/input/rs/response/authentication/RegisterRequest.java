package com.stockweb.demo.ports.input.rs.response.authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull(message = "el firstname no puede ser null ")
    @JoinColumn(name = "firstname")
    private String firstname;

    @NotNull(message = "el lastname no puede ser null ")
    @JoinColumn(name = "lastname")
    private String lastname;

    @Email
    @NotNull(message = "el email no puede ser null ")
    @JoinColumn(name = "email")
    private String email;

    @NotNull@NotNull(message = "el password no puede ser null ")
    @JoinColumn(name = "password")
    private String password;

}
