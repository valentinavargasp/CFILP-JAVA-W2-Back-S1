package com.alkemy.wallet.java;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.alkemy.wallet.controllers.PersonController;
import com.alkemy.wallet.models.user.Person;

@SpringBootTest
public class PersonServiceTests {


  @Test //  para los test se necesitan las dependencias de JUnit, q ya las tenemos
        @DisplayName("Test1 verifica si el usuario 1 esta ok")
        public void test1(){
              PersonController personController = new PersonController();
              ResponseEntity<Person> resp = personController.findPersonById(1);
              Assertions.assertThat(resp.getStatusCode());
           


        }
       
        @Test
        @DisplayName("Test2 verifica si buscar por nombre")
        public void test2(){
            PersonController personController3 = new PersonController();
            final ResponseEntity<Person> resp3 = personController3.findPersonByName("nadia");
             Assertions.assertThat(resp3.getStatusCode());
        }
          @Test
        @DisplayName("Test3 verifica si buscar por APELLIDO")
        public void test3(){
            PersonController personController4 = new PersonController();
            final ResponseEntity<Person> resp4 = personController4.findPersonByLastName("castro");
           // Assertions.assertThat(resp4.getStatusCode());
        }
}


/*
       
    @Test
        @DisplayName("Test2 verifica si buscar por identity Card esta ok")
        public void test2(){
            PersonController personController2 = new PersonController();
            //final ResponseEntity<Person> resp2 = personController2.findPersonByIdentityCard(28596216);
          //  Assertions.assertThat(resp2.getStatusCode());
        }
          */