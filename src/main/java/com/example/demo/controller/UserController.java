package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("isAuthenticated")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO, @PathVariable int id) {
        return ResponseEntity.ok().body(userService.updateUser(id, userRequestDTO));
    }

    @PreAuthorize("isAuthenticated")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable @Valid int id) {

        return ResponseEntity.ok().body(userService.deleteUser(id));

    }
    @PreAuthorize("isAuthenticated")
    @GetMapping(path = "/me")
    public ResponseEntity<UserResponseDTO> sendMyUser(@AuthenticationPrincipal User myUser) {
        return ResponseEntity.ok(userService.sendMyUser(myUser));
    }


}
