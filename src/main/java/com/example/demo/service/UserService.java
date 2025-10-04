package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USUARIO NAO ENCONTRADO"));
    }

    public User findByUserId(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("USUARIO NAO ENCONTRADO"));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.username(), passwordEncoder.encode(userRequestDTO.userpassword()), userRequestDTO.email(), Role.ROLE_USER);
        User saved = userRepository.save(user);
        return new UserResponseDTO((long) saved.getId(), saved.getUsername(), saved.getEmail(), saved.getRole());
    }


    public UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO) {

        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("USUARIO NAO ENCONTRADO"));

        if (!havePermission(userToUpdate.getId()) || !(userToUpdate.getId() == id)) {
            throw new AccessDeniedException("Você não tem permissão para atualizar este usuário");
        }

        userToUpdate.setPassword(passwordEncoder.encode(userRequestDTO.userpassword()));
        userToUpdate.setEmail(userRequestDTO.email());
        userToUpdate.setUsername(userRequestDTO.username());
        userRepository.save(userToUpdate);
        return new UserResponseDTO((long) userToUpdate.getId(), userToUpdate.getUsername(), userToUpdate.getEmail(), userToUpdate.getRole());


    }

    public UserResponseDTO deleteUser(int id) {
        User userToDelete = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("USUARIO NAO ENCONTRADO"));

        if (!havePermission(userToDelete.getId())) {
            throw new AccessDeniedException("Você não tem permissão para deletar este usuário");
        }
        userRepository.delete(userToDelete);
        return new UserResponseDTO((long) userToDelete.getId(), userToDelete.getUsername(), userToDelete.getEmail(), userToDelete.getRole());

    }


    public boolean havePermission(int userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getRole().name().equals(Role.ROLE_ADMIN.name()) || user.getId() == userId;
    }


    public UserResponseDTO sendMyUser(User user) {
        User user1 = userRepository.findById(user.getId()).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
        return new UserResponseDTO((long) user1.getId(), user1.getUsername(), user1.getEmail(), user1.getRole());

    }


}
