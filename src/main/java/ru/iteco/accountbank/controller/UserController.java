package ru.iteco.accountbank.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.accountbank.model.UserDto;
import ru.iteco.accountbank.service.UserService;
import ru.iteco.accountbank.validation.Create;
import ru.iteco.accountbank.validation.Update;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getById(id);
        ResponseCookie userId = ResponseCookie.from("userId", userDto.getId().toString()).maxAge(600).secure(true).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, userId.toString())
                .body(userDto);
    }

    @Validated(Create.class)
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @Validated(Update.class)
    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

}
