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
        Optional<RoleEntity> role = roleRepository.findById(userDto.getRoleId());
        if(role.isEmpty()){
            throw new EntityNotFoundException("El rol no existe");
        }
        UserEntity userEntity = UserMapper.toEntity(userDto);
        userEntity.setRole(role.get());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setDocType(docType.get());
        userEntity.setActive(true);
        return UserMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void updateUser(String username, String password, String role) {

    }

    @Override
    public void getUser(String username) {

    }

    @Override
    public void getUsers() {

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
