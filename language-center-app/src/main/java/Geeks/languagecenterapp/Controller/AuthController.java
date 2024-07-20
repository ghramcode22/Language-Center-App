package Geeks.languagecenterapp.Controller;

import Geeks.languagecenterapp.CustomExceptions.CustomException;
import Geeks.languagecenterapp.DTO.Request.LoginRequest;
import Geeks.languagecenterapp.DTO.Request.RegisterRequest;
import Geeks.languagecenterapp.Model.Enum.UserAccountEnum;
import Geeks.languagecenterapp.Model.UserEntity;
import Geeks.languagecenterapp.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute RegisterRequest registerRequest) {
        try {
            return new ResponseEntity<>(userService.registerUser(registerRequest), HttpStatus.CREATED);
        } catch (CustomException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBody());
        }
    }

    @PostMapping("/add/teacher")
    public ResponseEntity<?> addTeacher(@AuthenticationPrincipal UserEntity user, @ModelAttribute RegisterRequest registerRequest) {
        Map<String, String> response = new HashMap<>();
        if (user.getAccountType()!= UserAccountEnum.ADMIN){
            // Create a response object with the success message
            response.put("message","You are UnAuthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(userService.addTeacher(registerRequest), HttpStatus.CREATED);
        } catch (CustomException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBody());
        }
    }
    @PostMapping("/add/secretary")
    public ResponseEntity<?> addSecretary(@AuthenticationPrincipal UserEntity user, @ModelAttribute RegisterRequest registerRequest) {
        Map<String, String> response = new HashMap<>();
        if (user.getAccountType()!= UserAccountEnum.ADMIN){
            // Create a response object with the success message
            response.put("message","You are UnAuthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(userService.addSecretary(registerRequest), HttpStatus.CREATED);
        } catch (CustomException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBody());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @ModelAttribute LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(userService.loginUser(loginRequest), HttpStatus.OK);
        } catch (CustomException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBody());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return new ResponseEntity<>(userService.logout(request, response, authentication), HttpStatus.OK);
    }

}
