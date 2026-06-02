package org.learning_management_system.showcase.controller;


import dto.RegistrationRequestDTO;
import dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.learning_management_system.showcase.model.User;
import org.learning_management_system.showcase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.DTOMapper;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name="User Management", description="Endpoint for management of user account and authentication details")
public class UserController {

    private final UserService userService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome this endpoint is not secure");
    }

    @Operation(summary = "User registration", description = "Register a new user with the provided registration details and return the created user information")
    @PostMapping("/addNewUser")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody RegistrationRequestDTO userInfo){
        User user = DTOMapper.registrationRequestToUser(userInfo);
        User result = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(DTOMapper.userToUserDTO(result));
    }

    @Operation(summary="Password reset", description="Reset the password for a user with the provided email and new password. The new password cannot be the same as the old password.")
    @PostMapping("/resetPassword")
    public ResponseEntity<UserDTO> resetPassword(@RequestBody User userInfo){
        User result = userService.resetPassword(userInfo);
        return ResponseEntity.ok(DTOMapper.userToUserDTO(result));
    }

}
