package com.pk2024.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

import static com.pk2024.backend.settings.Settings.CHANGE_PASSWORD_NOT_THE_SAME_PASSWORDS;
import static com.pk2024.backend.settings.Settings.CHANGE_PASSWORD_WRONG_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            throw new IllegalStateException(CHANGE_PASSWORD_WRONG_PASSWORD);

        if (!request.getNewPassword().equals(request.getConfirmationPassword()))
            throw new IllegalStateException(CHANGE_PASSWORD_NOT_THE_SAME_PASSWORDS);

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);
    }
}
