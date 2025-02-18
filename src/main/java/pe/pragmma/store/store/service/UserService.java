package pe.pragmma.store.store.service;

import org.springframework.stereotype.Service;
import pe.pragmma.store.store.controller.dto.UserDto;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);
    void deleteUser(String username);
    void updateUser(String username, String password, String role);
    void getUser(String username);
    void getUsers();
}
