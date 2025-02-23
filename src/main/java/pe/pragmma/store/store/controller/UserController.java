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
   /**/
    @GetMapping("/auth")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.authenticate(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
