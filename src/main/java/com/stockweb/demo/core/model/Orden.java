package com.stockweb.demo.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_estado", nullable = false)
    @ToString.Exclude
    private EstadoOrden estadoOrden;

    @OneToMany (fetch = FetchType.EAGER)
    @JoinColumn(name="id_paquete")
    @ToString.Exclude
    private List<Paquete> paquetes;

    @Column (name = "precio_total")
    private float precioTotal;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "fecha_generada", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaGenerada;

    @Column (name = "fecha_finalizada")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaFinalizada;

    @Column (name = "fecha_modificacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaModificacion;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orden orden = (Orden) o;
        return Objects.equals(idOrden, orden.idOrden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden);
    }
}
