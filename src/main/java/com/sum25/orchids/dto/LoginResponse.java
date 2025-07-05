package com.sum25.orchids.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private long expriresIn;
    private String role;
    private String username;
}
