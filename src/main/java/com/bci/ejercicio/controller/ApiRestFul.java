package com.bci.ejercicio.controller;

import com.bci.ejercicio.dto.LoginRequest;
import com.bci.ejercicio.dto.MensajeResponse;
import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.entity.RolEntity;
import com.bci.ejercicio.entity.UserEntity;
import com.bci.ejercicio.exception.AuthenticationException;
import com.bci.ejercicio.jwt.JwtProvider;
import com.bci.ejercicio.service.RolService;
import com.bci.ejercicio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/userApiRestFul")
public class ApiRestFul {
    private static Logger LOG = LoggerFactory.getLogger(ApiRestFul.class);

    @Autowired
    UserService userService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RolService rolService;

    @PostMapping(value="createRol")
    public ResponseEntity<?> createRol(@RequestBody RolEntity rol)
    {
        LOG.info("createRol - Se recibió objeto rol: " + rol.toString());

        rolService.save(rol);

        return new ResponseEntity<Object>(new MensajeResponse("Rol creado con éxito"), HttpStatus.CREATED);
    }

    @PostMapping(value="saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO user)
    {
        LOG.info("saveUser - Se recibió objeto user: " + user.toString());

        return userService.saveUser(user);
    }

    @GetMapping(value="getUsers")
    public List<UserEntity> getUsers()
    {
        LOG.info("Obtener lista de usuarios desde el metodo rest getUsers");
        return userService.getUser();
    }


    @PostMapping(value="login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest)
            throws AuthenticationException {
        return userService.getTokenByUser(loginRequest.getUser());
    }
}
