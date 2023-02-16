package com.stockweb.demo.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "paquete")
public class Paquete {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private Long idPaquete;

    @Column (name = "precio_paquete", nullable = false, columnDefinition = "bigint default 0")
    private Long precioPaquete;


    @ManyToMany(mappedBy = "paquetes")
    @ToString.Exclude
    private Set<Producto> productos = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return Objects.equals(idPaquete, paquete.idPaquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaquete);
    }




}
