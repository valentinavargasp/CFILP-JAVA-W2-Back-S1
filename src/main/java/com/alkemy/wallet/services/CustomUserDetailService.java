package com.alkemy.wallet.services;

import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.utils.ColorLogger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Servicio personalizado para cargar detalles de usuario para autenticación
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepo;

    public CustomUserDetailService(UserRepository userRepo) {
        this.userRepo = Objects.requireNonNull(userRepo, "UserRepository no puede ser nulo");
    }
    
    /**
     * Carga un usuario por su nombre de usuario y asigna los roles correspondientes
     *
     * @param username el nombre de usuario a buscar
     * @return UserDetails con la información del usuario y sus roles
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo o vacío");
        }

        com.alkemy.wallet.models.user.User user = userRepo.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciales inválidas"));

        List<GrantedAuthority> authorities = user.getUserRoles() != null ? user.getUserRoles().stream()
                .filter(Objects::nonNull)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getRoleName()))
                .collect(Collectors.toList()) : Collections.emptyList();

        ColorLogger.green("CustomDetailService:");
        ColorLogger.green("Cargando el usuario: " + user.getUsername() + " con roles: " + authorities.toString());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),               
                authorities);
    }
}