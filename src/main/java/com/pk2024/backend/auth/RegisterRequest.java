package com.pk2024.backend.auth;

import com.pk2024.backend.settings.Settings;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be null or blank")
    private String email;

    @NotBlank(message = "Password cannot be null or blank")
    @Size(min = 6, message = "Password must be at least size of 6")
    private String password;
}
