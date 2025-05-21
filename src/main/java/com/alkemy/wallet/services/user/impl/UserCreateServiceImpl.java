package com.alkemy.wallet.services.user.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.models.user.UserRole;
import com.alkemy.wallet.repository.user.PersonRepository;
import com.alkemy.wallet.repository.user.RoleRepository;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.repository.user.UserRoleRepository;
import com.alkemy.wallet.services.user.UserCreateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    /**
     * Guarda un nuevo usuario a partir de un DTO.
     */
    @Override
    public UserCreateDTO saveUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }
        if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        try {
            //Se guarda la persona
            Person person = userMapper.toEntity(userCreateDTO.getPerson());
            personRepository.save(person);


            //Se guarda el usuario con la contraseña encriptada y el id de la persona
            User user = userMapper.toEntity(userCreateDTO);
            user.setPerson(person);
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            User savedUser = userRepository.save(user);

            System.out.println("Se guardo el usuario: " + savedUser);
            //Se guarda el rol del usuario
            //EL id 6 es el id del rol de usuario "CLIENTE"
            Role role = roleRepository.findById(6).orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
            userRoleRepository.save(new UserRole(savedUser, role));

            //Se guarda el usuario con el rol   
            //Las lineas 64 a 69 se puedeneliminar cuando funcione        
            User savedUserWithRole = userRepository.getReferenceById(savedUser.getId());
            System.out.println("----------------------------------");
            System.out.println("Se guardo el usuario con el rol: " + savedUserWithRole);
            System.out.println("----------------------------------");
            
            return userMapper.toCreateDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
