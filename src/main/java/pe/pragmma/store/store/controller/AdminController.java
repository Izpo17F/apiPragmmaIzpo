package pe.pragmma.store.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.pragmma.store.store.controller.dto.UserDto;
import pe.pragmma.store.store.service.UserService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/create-admin")
    public ResponseEntity<UserDto> createAdmin(@RequestBody @Valid UserDto userDto){
        UserDto userAdmin = userService.createAdmin(userDto);
        return new ResponseEntity<>(userAdmin, HttpStatus.CREATED);
    }
}
