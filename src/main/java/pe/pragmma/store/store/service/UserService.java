package pe.pragmma.store.store.service;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import pe.pragmma.store.store.controller.dto.LoginRequest;
import pe.pragmma.store.store.controller.dto.LoginResponse;
import pe.pragmma.store.store.controller.dto.UserDto;

import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto createAdmin(UserDto userAdmin);

    LoginResponse authenticate(LoginRequest loginResponse);

    void deleteUser(Integer userId);

    UserDto updateUser(UserDto userDto, Integer id);

    UserDto getUser(Integer id);

    List<UserDto> getUsers();
}
