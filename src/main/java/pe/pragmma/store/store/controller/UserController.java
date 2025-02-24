package pe.pragmma.store.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pragmma.store.store.controller.dto.LoginRequest;
import pe.pragmma.store.store.controller.dto.LoginResponse;
import pe.pragmma.store.store.controller.dto.UserDto;
import pe.pragmma.store.store.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {
        UserDto userDto = userService.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @GetMapping("/auth")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.authenticate(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Usuario con id(" + id +")  Borrado con exito! :C", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("id") Integer id){
        UserDto userDto1 = userService.updateUser(userDto, id);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
        UserDto userDto = userService.getUser(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping("/get-list")
    public ResponseEntity<List<UserDto>> getListUsers(){
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
