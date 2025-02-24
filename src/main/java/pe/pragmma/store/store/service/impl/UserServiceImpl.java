package pe.pragmma.store.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.pragmma.store.store.controller.dto.LoginRequest;
import pe.pragmma.store.store.controller.dto.LoginResponse;
import pe.pragmma.store.store.controller.dto.UserDto;
import pe.pragmma.store.store.exception.EntityNotFoundException;
import pe.pragmma.store.store.repository.DocTypeRepository;
import pe.pragmma.store.store.repository.RoleRepository;
import pe.pragmma.store.store.repository.UserRepository;
import pe.pragmma.store.store.repository.entity.DocTypeEntity;
import pe.pragmma.store.store.repository.entity.RoleEntity;
import pe.pragmma.store.store.repository.entity.UserEntity;
import pe.pragmma.store.store.service.UserService;
import pe.pragmma.store.store.service.mapper.UserMapper;
import pe.pragmma.store.store.util.JwtUtil;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DocTypeRepository docTypeRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<DocTypeEntity> docType = docTypeRepository.findById(userDto.getDocTypeId());
        if(docType.isEmpty()){
            throw new EntityNotFoundException("El tipo de documento no existe");
        }
        RoleEntity role = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role no encontrado"));
        UserEntity userEntity = UserMapper.toEntity(userDto);
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setDocType(docType.get());
        userEntity.setActive(true);
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto createAdmin(UserDto userAdmin) {
        Optional<DocTypeEntity> docType = docTypeRepository.findById(userAdmin.getDocTypeId());
        if(docType.isEmpty()){
            throw new EntityNotFoundException("El tipo de documento no existe");
        }
        RoleEntity role = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Role no encontrado"));
        UserEntity userEntity = UserMapper.toEntity(userAdmin);
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userAdmin.getPassword()));
        userEntity.setDocType(docType.get());
        userEntity.setActive(true);
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Integer userId) {
        UserEntity userEntity = userRepository.findByIdAndActive(userId, Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        userEntity.setActive(Boolean.FALSE);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        UserEntity userEntity = userRepository.findByIdAndActive(id,Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no existe!!"));
        userEntity.setUsername(userDto.getUsername());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto getUser(Integer id) {
        return UserMapper.toDto( userRepository.findByIdAndActive(id,Boolean.TRUE)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no existe!!")));
    }

    @Override
    public List<UserDto> getUsers() {
            return UserMapper.toListDto(userRepository
                    .findAllActive(Boolean.TRUE));
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                UserEntity userEntity = userRepository.findByUsernameAndActive(loginRequest.getUsername(), Boolean.TRUE)
                        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado o inactivo"));

                String token = jwtUtil.generateToken(userEntity.getUsername(), userEntity.getRole().getName());
                return new LoginResponse(token, UserMapper.toDto(userEntity));
            } else {
                throw new BadRequestException("Las credenciales son incorrectas o el usuario no está activo.");
            }
        } catch (Exception e) {
            return new LoginResponse("Error durante la autenticación: " + e.getMessage(), null);
        }
    }

}
