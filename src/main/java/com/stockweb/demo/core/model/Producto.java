package com.stockweb.demo.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column (nullable = false,name = "producto")
    private String producto;

    @Column (nullable = false,name = "stock", columnDefinition = "bigint default 0")
    private Long stock;

    @Column (nullable = false,name = "precio", columnDefinition = "bigint default 0")
    private Float precio;

    @Column (nullable = false,name = "img_url", columnDefinition = "varchar(255) default -")
    private String imgUrl;

    @Column (nullable = false,name = "descripcion", columnDefinition = "varchar(255) default -")
    private String descripcion;

    @Column (nullable = false,name = "fecha_ultimo_inv", columnDefinition = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
