package com.stockweb.demo.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Rol implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_rol", nullable = false)
    private long idRol;

    @Column(name = "rol_name", nullable = false)
    private String rolName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return  Objects.equals(idRol, rol.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol);
    }

    @Override
    public String getAuthority() {
        return this.rolName;
    }
}
