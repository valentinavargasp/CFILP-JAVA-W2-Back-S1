package com.alkemy.wallet.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.models.user.User;

public class UserMapperTest {
// ✅ Mapeador sin contexto Spring
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testToDTO() {
        // 🧪 GIVEN: User con una Person asociada
        Person person = new Person();
        person.setName("Ana");
        person.setLastName("Gómez");

        User user = new User();
        user.setId(1);
        user.setUsername("anagomez");
        user.setPerson(person);

        // 🎯 WHEN: Convertimos a DTO
        UserDTO dto = userMapper.toDTO(user);

        // ✅ THEN: Verificamos los valores mapeados
        assertEquals(1, dto.getId());
        assertEquals("anagomez", dto.getUsername());
    }

    @Test
    void testToEntity() {
        // 🧪 GIVEN: UserDTO simple
        UserDTO dto = new UserDTO();
        dto.setId(2);
        dto.setUsername("mariarosa");

        // 🎯 WHEN: Convertimos a Entity
        User user = userMapper.toEntity(dto);

        // ✅ THEN: Verificamos
        assertEquals(2, user.getId());
        assertEquals("mariarosa", user.getUsername());
    }
}
