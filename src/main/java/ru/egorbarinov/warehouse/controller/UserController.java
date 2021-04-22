package ru.egorbarinov.warehouse.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.warehouse.dto.UserDto;
import ru.egorbarinov.warehouse.exception.ResourceNotFoundException;
import ru.egorbarinov.warehouse.exception.WarehouseException;
import ru.egorbarinov.warehouse.service.user_service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        UserDto userDto = userService.getById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getList() {
        return userService.findAll();
    }

    // создание пользователя
    @PostMapping("/users")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) throws ResourceNotFoundException, WarehouseException {
        userDto.setId(null);
        return ResponseEntity.ok(userService.save(userDto));
    }

    // изменение пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDto userDto)
            throws ResourceNotFoundException, WarehouseException {
        userDto.setId(userId);  // игнорим, то что пришло в DTO
        return ResponseEntity.ok(userService.save(userDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userService.delete(userId);
        return ResponseEntity.ok("Deleted");
    }

//    @PostMapping("/users/set-pass")
//    public ResponseEntity<String> setUserPassword(@RequestBody UserDto userDto) throws ResourceNotFoundException, WarehouseException {
//        userService.assignPassword(userDto);
//        return ResponseEntity.ok("Еhe password is set");
//    }

}

