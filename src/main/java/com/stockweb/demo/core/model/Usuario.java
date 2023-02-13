package com.stockweb.demo.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ToString.Exclude
    private Rol rol;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.rol != null) {
            return Collections.singleton(this.rol);
        }
        return Collections.emptySet();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
