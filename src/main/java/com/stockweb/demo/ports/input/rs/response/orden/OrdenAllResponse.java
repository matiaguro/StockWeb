package com.stockweb.demo.ports.input.rs.response.orden;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockweb.demo.ports.input.rs.response.cliente.ClienteResponse;
import com.stockweb.demo.ports.input.rs.response.paquete.PaqueteResponse;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrdenAllResponse {


    @JsonProperty("id_orden")
    private Long idOrden;

    @JsonProperty("nombre_user")
    private String nombreUsuario;

    @JsonProperty("cliente")
    private ClienteResponse cliente;

    @JsonProperty("clave_estado")
    private String claveEstado;

    @JsonProperty("paquetes")
    private List<PaqueteResponse> paquetes;

    @JsonProperty ("precio_total")
    private Float precioTotal;

    @JsonProperty ("descripcion")
    private String descripcion;

    @JsonProperty ("fecha_generada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ",timezone = "GMT-3")
    private Date fechaGenerada;

    @JsonProperty ("fecha_finalizada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ",timezone = "GMT-3")
    private Date fechaFinalizada;

    @JsonProperty ("fecha_devolucion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ",timezone = "GMT-3")
    private Date fechaDevolucion;

    @JsonProperty ("fecha_modificacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss ",timezone = "GMT-3")
    private Date fechaModificacion;

    @JsonProperty ("adelanto")
    private Boolean adelanto;

}
