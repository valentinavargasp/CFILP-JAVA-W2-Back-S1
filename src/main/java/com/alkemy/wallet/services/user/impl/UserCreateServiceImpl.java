package com.alkemy.wallet.services.user.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.mapper.PersonMapper;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.account.AccountType;
import com.alkemy.wallet.models.account.Currency;
import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.models.user.UserRole;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.account.AccountTypeRepository;
import com.alkemy.wallet.repository.user.PersonRepository;
import com.alkemy.wallet.repository.user.RoleRepository;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.repository.user.UserRoleRepository;
import com.alkemy.wallet.services.user.UserCreateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserMapper userMapper;
    private final PersonMapper personMapper;

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
            // Se guarda la persona
            Person person = personMapper.toEntity(userCreateDTO.getPerson());
            personRepository.save(person);

            // Se guarda el usuario con la contraseña encriptada y el id de la persona
            User user = userMapper.toEntity(userCreateDTO);
            user.setPerson(person);
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
            User savedUser = userRepository.save(user);

            System.out.println("Se guardo el usuario: " + savedUser);
            // Se guarda el rol del usuario
            // EL id 5 es el id del rol de usuario "CLIENTE"
            Role role = roleRepository.findById(5).orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
            userRoleRepository.save(new UserRole(savedUser, role));

            //SE LE ASIGNA UNA CUENTA A LA PERSONA

            AccountType accountType = accountTypeRepository.findById(1)
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de cuenta no encontrado"));
            // Crear una cuenta asociada al usuario recién creado
            Account account = new Account();
            account.setCbu("CBU_" + savedUser.getId()); // Generar un CBU único
            account.setAlias("alias_" + savedUser.getUsername().toLowerCase());
            account.setBalance(0.0);
            account.setAccountType(accountType);
            account.setCurrency(Currency.ARS);
            account.setUser(savedUser);
            account.setAccountName(accountType.getAccountType() + " en " + Currency.ARS); 
            accountRepository.save(account);

            User updatedUser = userRepository.save(savedUser);

            System.out.println("----------------------------------");
            System.out.println("Se guardo el usuario con el rol y la cuenta: " + updatedUser);
            System.out.println("----------------------------------");

            return userMapper.toCreateDTO(updatedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
