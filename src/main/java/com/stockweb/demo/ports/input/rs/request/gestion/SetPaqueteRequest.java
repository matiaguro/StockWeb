package com.stockweb.demo.ports.input.rs.request.gestion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetPaqueteRequest {

    @JsonProperty("idOrden")
    @NotNull(message = "El id orden no puede ser null")
    private Long idOrden;

    @JsonProperty("idPaquetes")
    @NotNull(message = "El id del paquete no puede ser null")
    private List<Long> idPaquetes;

}
