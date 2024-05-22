package com.pk2024.backend.auth;

import com.pk2024.backend.user.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.pk2024.backend.settings.Settings.AUTH_MAPPING;

@RestController
@RequestMapping(AUTH_MAPPING)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception ignored) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }


    @GetMapping("/user-token")
    public ResponseEntity<UserDTO> getUserByToken(
            HttpServletRequest request
    ) throws IOException {
        return service.findUserDTOByToken(request);
    }

}
