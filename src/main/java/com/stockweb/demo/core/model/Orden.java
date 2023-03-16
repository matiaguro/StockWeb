package com.stockweb.demo.core.model;

import com.stockweb.demo.config.exception.ErrorExpected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    @ToString.Exclude
    private EstadoOrden estadoOrden;

    @OneToMany(mappedBy = "orden")
    private List<Paquete> paquetes;

    @Column(name = "precio_total")
    private float precioTotal;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_generada", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaGenerada;

    @Column(name = "fecha_finalizada")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaFinalizada;

    @Column(name = "fecha_modificacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaModificacion;

    @Column(name = "adelanto")
    private Boolean adelanto;


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


    public void validarContenido() {

        if(paquetes !=null || !paquetes.isEmpty()){

            for (Paquete paquete : paquetes) {
                if (paquete.getDescPaqueteList() == null || paquete.getDescPaqueteList().isEmpty() ){
                    throw new ErrorExpected("La orden debe tener al menos un paquete con contenido", HttpStatus.BAD_REQUEST);

                }
            }
        }else {
            throw new ErrorExpected("La orden debe tener al menos un paquete con contenido", HttpStatus.BAD_REQUEST);
        }

    }

    public void validEstadoGenerada(String errorMensaje){
        if (estadoOrden.getIdEstado() != 1)
            throw new ErrorExpected(errorMensaje, HttpStatus.BAD_REQUEST);

    }



}