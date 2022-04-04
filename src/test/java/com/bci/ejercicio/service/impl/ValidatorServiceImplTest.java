package com.bci.ejercicio.service.impl;

import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.exception.BusinessException;
import com.bci.ejercicio.exception.RequestException;
import com.bci.ejercicio.service.ValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorServiceImplTest {

    @Autowired
    ValidatorService validatorService;

    @Test
    void isValidPasswordFalse() {

        UserRequestDTO user = new UserRequestDTO();
        String email = "juan@rodriguez.org";
        String password = "nu123Hhh";
        user.setEmail(email);
        user.setPassword(password);
        Exception exception = assertThrows(RequestException.class, () -> {
            validatorService.validate(user);
        });
        String expectedMessage = "El password no cumple con las especificaciones, debe tener al menos 1 numero, una letra mayuscula y minuscula, un caracter especial y minimo 5 caracteres";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void isValidEmailFalse() {

        UserRequestDTO user = new UserRequestDTO();
        String email = "juanrodriguez.org";
        String password = "nu123H#hh";
        user.setEmail(email);
        user.setPassword(password);
        Exception exception = assertThrows(RequestException.class, () -> {
            validatorService.validate(user);
        });
        String expectedMessage = "Email con formato incorrecto";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}