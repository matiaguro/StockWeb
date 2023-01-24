package com.stockweb.demo.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "producto")
public class Producto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto")
    private Long idProducto;

    @Column (nullable = false)
    private String producto;

    @Column (nullable = false, columnDefinition = "bigint default 0")
    private Long stock;

    @Column (nullable = false, columnDefinition = "bigint default 0")
    private Long precio;

    @Column (nullable = false, columnDefinition = "varchar(255) default -")
    private String imgUrl;

    @Column (nullable = false, columnDefinition = "varchar(255) default -")
    private String descripcion;

    @Column (nullable = false, columnDefinition = "datetime")
    private Date fechaUltimoInv;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

}
