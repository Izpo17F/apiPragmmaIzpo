package pe.pragmma.store.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DocTypeRepository docTypeRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

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
}
