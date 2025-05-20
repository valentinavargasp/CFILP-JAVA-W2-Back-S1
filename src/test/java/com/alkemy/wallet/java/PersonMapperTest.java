package com.alkemy.wallet.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.alkemy.wallet.dto.PersonDTO;
import com.alkemy.wallet.mapper.PersonMapper;
import com.alkemy.wallet.models.user.Person;

public class PersonMapperTest {
 // ✅ Usamos MapStruct directamente, sin Spring
    private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Test
    void testToDTO() {
        // 🧪 GIVEN: Creamos una entidad Person simulada
        Person person = new Person();
        person.setIdPerson(1);
        person.setName("Lucía");
        person.setLastName("Pérez");
        person.setAddress("Av. Siempre Viva 123");
        person.setLocation("Buenos Aires");
        person.setProvince("BA");
        person.setPhoneNumber("1123456789");
        person.setIdentityCard(12345678);
        person.setDateBirth("1990-01-01");

        // 🎯 WHEN: Convertimos a DTO
        PersonDTO dto = personMapper.toDTO(person);

        // ✅ THEN: Verificamos los campos mapeados
        assertEquals(1, dto.getIdPerson());
        assertEquals("Lucía", dto.getName());
        assertEquals("Pérez", dto.getLastName());
        assertEquals("Buenos Aires", dto.getLocation());
        assertEquals("1123456789", dto.getPhoneNumber());
    }

    @Test
    void testToEntity() {
        // 🧪 GIVEN: Creamos un DTO simulado
        PersonDTO dto = new PersonDTO();
        dto.setIdPerson(5);
        dto.setName("Sofía");
        dto.setLastName("Gómez");
        dto.setLocation("Mendoza");
        dto.setPhoneNumber("1133344455");

        // 🎯 WHEN: Convertimos a entidad
        Person person = personMapper.toEntity(dto);

        // ✅ THEN: Verificamos los campos mapeados
        assertEquals(5, person.getIdPerson());
        assertEquals("Sofía", person.getName());
        assertEquals("Gómez", person.getLastName());
        assertEquals("Mendoza", person.getLocation());
        assertEquals("1133344455", person.getPhoneNumber());
    }
}