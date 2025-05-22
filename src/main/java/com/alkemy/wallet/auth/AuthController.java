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
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.services.user.UserCreateService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserCreateService userCreateService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserCreateService userCreateService,
            UserRepository userRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userCreateService = userCreateService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(userDetails, user.getId());

        System.out.println("login exitoso del usuario: " + authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "Crear nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @PostMapping("/register")
    public ResponseEntity<UserCreateDTO> saveUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserCreateDTO savedUser = userCreateService.saveUser(userCreateDTO); // Si hay algún error de validación, lo
                                                                             // manejará el GlobalExceptionHandler
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Si todo va bien devuelve un 201 Created
    }

}
