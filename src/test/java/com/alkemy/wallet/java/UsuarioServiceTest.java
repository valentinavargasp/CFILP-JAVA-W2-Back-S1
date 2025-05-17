package com.alkemy.wallet.java;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.alkemy.wallet.controllers.UserController;
import com.alkemy.wallet.models.user.User;

@SpringBootTest
public class UsuarioServiceTest {
       
    	@Test //  para los test se necesitan las dependencias de JUnit, q ya las tenemos
        @DisplayName("primer test prueba")
        public void test1(){
           
           UserController userController = new UserController();
          // final ResponseEntity<User> resp = userController.getUserById(1);
         //  Assertions.assertEquals(resp.getStatusCode(), 200);

           //assertEquals(int expected, int actual) Afirma que expectedy actualson iguales.
             
           
            // final User resp1 = userController.getUserById(1L);
           // Assertions.assertTrue(resp1);    
            //Assertions.assertThat("","");
               // assertEquals(resp1.getStatusCode(), HttpStatus.OK);
            

                // Verificamos el cuerpo del objeto
              //  assertNotSame(responseEntity1.getBody(), responseEntity2.getBody()); // Comprobamos que no sean el mismo objeto en memoria.
               // assertNotEquals(responseEntity1.getBody(), responseEntity2.getBody()); // Comprobamos que los contenidos sean diferentes.
    }
 	@Test //  para los test se necesitan las dependencias de JUnit, q ya las tenemos
    @DisplayName("Test 2 - Userrrrr")
    public void test2(){
       //  UserController userController = new UserController();
        
         



    }


        }
    


