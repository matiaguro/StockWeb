package com.stockweb.demo.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente", nullable = false)
    private Long idCliente;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="email", nullable = false)
    private String email;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }
}
