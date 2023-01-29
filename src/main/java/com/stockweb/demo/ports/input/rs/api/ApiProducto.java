package com.stockweb.demo.ports.input.rs.api;


        import com.stockweb.demo.ports.input.rs.request.producto.ProductoRequest;
        import com.stockweb.demo.ports.input.rs.request.producto.ProductoRequestAmount;
        import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponse;
        import com.stockweb.demo.ports.input.rs.response.producto.ProductoResponseLista;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.validation.Valid;
        import javax.validation.constraints.NotNull;
        import java.util.Optional;

@Validated
public interface ApiProducto {


    ResponseEntity<Void> createProducto(@Valid @RequestBody ProductoRequest productoRequest);

    void upDateProducto(@NotNull @PathVariable Long id, @Valid @RequestBody ProductoRequest productoRequest);

    void upDateStock(@NotNull @PathVariable Long id, @Valid @RequestBody ProductoRequestAmount productoRequestAmount);

    void DeleteProducto(@NotNull @PathVariable Long idProducto);

    ResponseEntity<ProductoResponseLista> getAllProductos(@RequestParam Optional<Integer> page,
                                                                 @RequestParam Optional<Integer> size);
    ResponseEntity<ProductoResponse> findById(@NotNull @PathVariable Long idProducto);
}