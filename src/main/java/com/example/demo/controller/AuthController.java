package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.loginLimit.LoginAttemptService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    LoginAttemptService loginAttemptService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        String username = loginRequestDTO.username();

        if (!loginAttemptService.podeTentar(username)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("MUITAS TENTATIVAS, AGUARDE ALGUNS SEGUNDOS");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.userpassword()));

            loginAttemptService.reset(username);
            String token = jwtUtils.generateToken(authentication.getName());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            loginAttemptService.registerFailure(username);
            long delay = loginAttemptService.getDelaySeconds(username);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Aguarde " + delay + " segundos antes de tentar novamente.");
        }
    }

    //TRATAR EXCESSOES
    @PostMapping(path = "/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws IllegalAccessException {

        try {
            return ResponseEntity.ok(userService.createUser(userRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
