package com.bci.ejercicio.service.impl;

import com.bci.ejercicio.dto.LoginResponse;
import com.bci.ejercicio.dto.PhoneDTO;
import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.dto.UserResponseDTO;
import com.bci.ejercicio.entity.PhoneEntity;
import com.bci.ejercicio.entity.RolEntity;
import com.bci.ejercicio.entity.UserEntity;
import com.bci.ejercicio.exception.BusinessException;
import com.bci.ejercicio.exception.RequestException;
import com.bci.ejercicio.exception.UnexpectedException;
import com.bci.ejercicio.jwt.JwtProvider;
import com.bci.ejercicio.repository.PhoneRepository;
import com.bci.ejercicio.repository.UserRepository;
import com.bci.ejercicio.service.RolService;
import com.bci.ejercicio.service.UserService;
import com.bci.ejercicio.service.ValidatorService;
import com.bci.ejercicio.util.RolName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("UserRepository")
    UserRepository userRepository;
    @Autowired
    @Qualifier("PhoneRepository")
    PhoneRepository phoneRepository;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    ValidatorService validatorService;

    public List<UserEntity> getUser() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public ResponseEntity<Object> saveUser(UserRequestDTO user) {
        try{
            LOG.info("Obetenemos el Entity de User desde objeto input");

            validatorService.validate(user);

            UserEntity userEntity = getUserEntity(user);

            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
            userEntity.setRoles(roles);

            UserResponseDTO userResponse = getUserResponseDTO(user, userEntity);

            return new ResponseEntity<Object>(userResponse, HttpStatus.CREATED);
        } catch (RequestException | BusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UnexpectedException("Ha ocurrido un error inesperado");
        }
    }

    private UserResponseDTO getUserResponseDTO(UserRequestDTO user, UserEntity userEntity) {
        UserEntity userEntityResult = new UserEntity();
        userEntityResult = userRepository.save(userEntity);
        LOG.info("obtenemos el result de user: " + userEntityResult.toString());
        savePhoneEntity(user, userEntityResult);

        String token = jwtProvider.generateToken(user.getEmail());

        UserResponseDTO userResponse = new UserResponseDTO(userEntityResult);

        userResponse.setToken(token);
        return userResponse;
    }

    private UserEntity getUserEntity(UserRequestDTO user) {
        UserEntity userEntity = new UserEntity(user);

        LocalDate localDate = LocalDate.now();
        Date createdDate = Date.valueOf(localDate);

        userEntity.setCreated(createdDate);
        userEntity.setModified(createdDate);
        userEntity.setLast_login(createdDate);
        userEntity.setIsactive(true);
        return userEntity;
    }

    private void savePhoneEntity(UserRequestDTO user, UserEntity userEntityResult) {
        LOG.info("Obetenemos el Entity de Phone desde objeto input");

        for(PhoneDTO phoneDTO: user.getPhones())
        {
            PhoneEntity phoneEntity = new PhoneEntity(phoneDTO);
            phoneEntity.setId(userEntityResult.getId());
            phoneRepository.save(phoneEntity);
        }
    }


    public Optional<UserEntity> findByEmail(String email) {
        LOG.info("Entramos desde userService al repositorio a buscar Usuario por Email");

        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> getTokenByUser(String user) {

        if(!userRepository.existsByName(user)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "El usuario No existe");
        }

        UserEntity usuario = userRepository.findByName(user).get();

        String token = jwtProvider.generateToken(usuario.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        return new ResponseEntity<Object>(loginResponse, HttpStatus.OK);
    }
}
