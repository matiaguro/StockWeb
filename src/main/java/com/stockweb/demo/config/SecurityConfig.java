package com.stockweb.demo.config;


import com.stockweb.demo.config.exception.handler.AuthenticationEntryPointHandler;
import com.stockweb.demo.config.exception.handler.CustomAccessDeniedHandler;
import com.stockweb.demo.config.security.JwtAuthFilter;
import com.stockweb.demo.ports.input.rs.api.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeHttpRequests()
            //Config Auth
            .antMatchers(HttpMethod.POST, ApiConstants.AUTH_URI +"/login").permitAll()
            .antMatchers(HttpMethod.POST,ApiConstants.AUTH_URI +"/register" ).hasRole("ADMIN")
            //Config User
            .antMatchers(HttpMethod.PATCH,ApiConstants.USUARIOS_URI +"/updatePassword/{idUsuario}" ).hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE,ApiConstants.USUARIOS_URI +"/{idUsuario}" ).hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH,ApiConstants.USUARIOS_URI +"/editUsuario/{idUsuario}" ).hasRole("ADMIN")
            .antMatchers(HttpMethod.GET,ApiConstants.USUARIOS_URI +"getAllUsuarios").hasRole("ADMIN")
            //Config Product
            .antMatchers(HttpMethod.POST, ApiConstants.PRODUCT_URI ).authenticated()
            .antMatchers(HttpMethod.PUT, ApiConstants.PRODUCT_URI + "/editProducto/{idProducto}").authenticated()
            .antMatchers(HttpMethod.PATCH, ApiConstants.PRODUCT_URI + "/{idProducto}" ).authenticated()
            .antMatchers(HttpMethod.DELETE, ApiConstants.PRODUCT_URI + "/{idProducto}").authenticated()
            .antMatchers(HttpMethod.GET, ApiConstants.PRODUCT_URI + "/allProductos").authenticated()
            .antMatchers(HttpMethod.GET, ApiConstants.PRODUCT_URI + "/byId/{idProducto}").authenticated()
            //Config Package
            .antMatchers(HttpMethod.POST, ApiConstants.PAQUETE_URI).authenticated()
            .antMatchers(HttpMethod.PATCH, ApiConstants.PAQUETE_URI+"/agregarProductos/{idPaquete}").authenticated()
            .antMatchers(HttpMethod.PATCH, ApiConstants.PAQUETE_URI+"/sacarProductos/{idPaquete}").authenticated()
            .antMatchers(HttpMethod.PATCH, ApiConstants.PAQUETE_URI+"/agregarProductos/{idPaquete}").authenticated()
            .antMatchers(HttpMethod.DELETE, ApiConstants.PAQUETE_URI+"/{idPaquete}").authenticated()
            .antMatchers(HttpMethod.GET, ApiConstants.PAQUETE_URI+"findPaquete/{idPaquete}").authenticated()
            // Default access for each Method
            .antMatchers(HttpMethod.GET).authenticated()
            .antMatchers(HttpMethod.POST).hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH).hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
            // Error handling
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new AuthenticationEntryPointHandler())
            .accessDeniedHandler(new CustomAccessDeniedHandler())
            // Session
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // filters
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
}
