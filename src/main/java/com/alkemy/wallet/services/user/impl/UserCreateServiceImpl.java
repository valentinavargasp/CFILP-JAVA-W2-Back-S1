package com.alkemy.wallet.services.user.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.user.PersonRepository;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.services.user.UserCreateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;

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

            System.out.println("......");
            System.out.println("User saved: " + savedUser);
            System.out.println("......");

            //TODO: Debería guardar el rol del usuario aquí con savedUser.getId()

            return userMapper.toCreateDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
