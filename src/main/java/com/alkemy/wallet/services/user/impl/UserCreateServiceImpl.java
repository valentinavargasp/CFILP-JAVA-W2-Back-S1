package com.alkemy.wallet.services.user.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.services.user.UserCreateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Guarda un nuevo usuario a partir de un DTO.
     */
    @Override
    public UserCreateDTO saveUser(UserCreateDTO userCreateDTO) {
        try {
            User user = userMapper.toEntity(userCreateDTO);
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            User savedUser = userRepository.save(user);
            System.out.println("......");
            System.out.println("User saved: " + savedUser);
            System.out.println("......");
            return userMapper.toCreateDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}
