package com.alkemy.wallet.auth;

import com.alkemy.wallet.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.services.user.UserCreateService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserCreateService userCreateService;


    public AuthController(AuthenticationManager authManager, JwtService jwtService,UserCreateService userCreateService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userCreateService = userCreateService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        System.out.println("login exitoso del usuario: " + authRequest.getUsername());

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

      //UserCreateDTO es un DTO que contiene solo los campos necesarios para crear un usuario
    //En este caso, solo el username y el password. El resto de los campos se rellenan automáticamente
    
    @Operation(summary = "Crear nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @PostMapping("/register")
    public ResponseEntity<UserCreateDTO> saveUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserCreateDTO savedUser = userCreateService.saveUser(userCreateDTO); //Si hay algún error de validación, lo manejará el GlobalExceptionHandler
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Si todo va bien devuelve un 201 Created
    }
    

}

