package com.bayraktar.graduationproject.springboot.controller;

import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.exception.NotFoundException;
import com.bayraktar.graduationproject.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveNewUser(@RequestBody @Valid UserDto userDto) {
        userDto = userService.saveUser(userDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateExistingUser(@RequestBody @Valid UserDto userDto) {
        userDto = userService.updateUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserById(@RequestParam Long id) {
        if(userService.deleteUser(id) > 0) {
            return ResponseEntity.ok("User " + id + " deleted");
        }
        throw new NotFoundException("User " + id + "doesn't exist.");
    }
}
