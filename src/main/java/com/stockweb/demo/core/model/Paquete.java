package com.stockweb.demo.core.model;

import com.stockweb.demo.config.exception.ErrorExpected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paquete")
public class Paquete {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private Long idPaquete;

    @Column (name = "nombre_paquete", columnDefinition = "varchar(100) default Paquete")
    private String nombrePaquete;

    @OneToMany(mappedBy = "paquete", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<DescPaquete> descPaqueteList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden")
    private Orden orden;

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


    public void validOrden(){
        if (orden == null){
            throw new ErrorExpected("El paquete no tiene orden asignada", HttpStatus.BAD_REQUEST);

        }

    }



}
