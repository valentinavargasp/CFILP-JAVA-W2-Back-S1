package com.alkemy.wallet.services.user.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.alkemy.wallet.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Devuelve todos los usuarios como DTOs.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Edita un usuario por ID y devuelve el nuevo UserDTO.
     */
    @Override
    public UserDTO editUserById(int id, UserDTO newUserData) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));

        // Actualiza los campos editables
        user.setUsername(newUserData.getUsername());
        // NOTA: no tocamos password ni roles ni accounts por ahora

        // Guarda cambios y retorna como DTO
        User updated = userRepository.save(user);
        return userMapper.toDTO(updated);
    };

    /**
     * Elimina un usuario por ID.
     */
    @Override
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Usuario no encontrado con id " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Obtiene un usuario por su ID.
     */
    @Override
    @Transactional()
    public UserDTO getUserById(int id) {
        User user = userRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return userMapper.toDTO(user);
    }

    

}
