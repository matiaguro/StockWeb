package com.stockweb.demo.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "desc_paquete")
public class DescPaquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desc_paquete")
    private Long idDescPaquete;

    @Column(name = "cantidad")
    private Long cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paquete")
    @ToString.Exclude
    private Paquete paquete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ToString.Exclude
    private Producto producto;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescPaquete descPaquete = (DescPaquete) o;
        return Objects.equals(idDescPaquete, descPaquete.idDescPaquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDescPaquete);
    }




}
