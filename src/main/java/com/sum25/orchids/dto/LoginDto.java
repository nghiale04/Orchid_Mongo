package com.sum25.orchids.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {
    @Email(message = "Invalid email format")
    private String email;
    private String password;
}
