package com.bci.ejercicio.service.impl;

import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.exception.BusinessException;
import com.bci.ejercicio.exception.RequestException;
import com.bci.ejercicio.repository.UserRepository;
import com.bci.ejercicio.service.ValidatorService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Autowired
    @Qualifier("UserRepository")
    UserRepository userRepository;

    @Value("${password.user.regex}")
    String passRegex;

    @Override
    public void validate(UserRequestDTO user){
        validateEmailFormat(user);
        validateEmailExist(user);
        validatePasswordFormat(user);
    }

    private void validateEmailFormat(UserRequestDTO user) {
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new RequestException("Email con formato incorrecto");
        }
    }

    private void validateEmailExist(UserRequestDTO user) {
        if (existsByEmail(user.getEmail())) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "El correo ya esta registrado");
        }
    }

    private void validatePasswordFormat(UserRequestDTO user) {
        if (!isPasswordValid(passRegex, user.getPassword())) {
            throw new RequestException("El password no cumple con las especificaciones, debe tener al menos 1 numero, una letra mayuscula y minuscula, un caracter especial y minimo 5 caracteres");
        }
    }

    private boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    private boolean isPasswordValid (String passRegex, String password){

        Pattern pattern = Pattern.compile(passRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
