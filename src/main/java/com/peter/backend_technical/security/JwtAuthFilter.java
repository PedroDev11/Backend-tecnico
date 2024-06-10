package com.peter.backend_technical.security;

import java.io.IOException;

import com.peter.backend_technical.handler.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.peter.backend_technical.entity.UsuariosEntity;
import com.peter.backend_technical.repository.UsuariosRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtIO jwtService;

    @Autowired
    private UsuariosRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.- Obtener el Header que contiene el token
        String authHeader = request.getHeader("Authorization");
        System.out.println("Header ----> " + authHeader);

        // 1.1.- Validando que se envie el token mediante el header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Tambien necesitamos que pase por el Authorization filter para ver si este endpoint es
            // publico, si es publico no necesitamos mandar el Authorization con el jwt
            filterChain.doFilter(request, response);

            // Retornamos el control, que se salga del método y que no ejecute el codigo de abajo
            return;
        }

        // 2.- Obtener el jwt
        // split genera un array de Strings en base a un punto de separacion, en este caso un espacio
        // vacio debido a que en el Header viene separado el tipo Bearer con el token jwt. y accedemos
        // a la posicion 1 que es donde se encuentra el token.
        String jwt = authHeader.split(" ")[1];
        System.out.println("JWT ----> " + jwt);

        // 3.- Obtener el subject/username desde el jwt
        String nombre = jwtService.getPayload(jwt);
        System.out.println("username getPayload() " + nombre);

        // 3.1.- Obtener los authorities del usuario, .get() para que no mande esa exception
        UsuariosEntity user = userRepository.findByNombre(nombre)
				.orElseThrow(() -> new AppException("Usuario no encontrado en la base de datos", HttpStatus.NOT_FOUND));

        // 4.- Setear un objeto Authentication dentro del SecurityContext
        // Credentials = null, Las credenciales solo son necesarias cuando mandamos a llamar el metodo
        // authenticate del AuthenticationManager y eso se hizo cuando generamos el token en el login.
        /*UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                nombre, null, Collections.emptyList()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);*/

        // 5.- Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
