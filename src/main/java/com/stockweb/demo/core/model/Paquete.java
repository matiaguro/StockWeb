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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
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

    @Column (name = "nombre_paquete", columnDefinition = "varchar(100) default Paquete")
    private String nombrePaquete;

    @Column (name = "precio_paquete", columnDefinition = "bigint default 0")
    private Long precioPaquete;



    @ManyToMany
    @JoinTable(name = "producto_paquete",
            joinColumns = @JoinColumn(name = "id_paquete"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    @ToString.Exclude
    private List<Producto> productos;

    public void addProducto (Producto producto){
        productos.add(producto);
    }

    public void deleteProducto (Producto producto){
        productos.remove(producto);
    }

    public Producto selectProductoById(Long idProducto){
        for (Producto producto : productos){
            if (producto.getIdProducto()==idProducto){
                return producto;
            }
        }
        return null;
    }


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
