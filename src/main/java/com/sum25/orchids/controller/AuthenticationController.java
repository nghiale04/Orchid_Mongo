package com.sum25.orchids.controller;

import com.sum25.orchids.dto.LoginDto;
import com.sum25.orchids.dto.LoginResponse;
import com.sum25.orchids.dto.RegisterUserDto;
import com.sum25.orchids.dto.ResponseDTO;
import com.sum25.orchids.entity.Accounts;
import com.sum25.orchids.services.AuthenticationService;
import com.sum25.orchids.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        Accounts accounts = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(ResponseDTO.builder()
                .statusCode("200")
                .message("User registered successfully")
                .data(accounts)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDto loginDto) {
        Accounts accounts = authenticationService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(accounts);
        LoginResponse loginResponse = LoginResponse.builder()
                        .token(jwtToken)
                        .expriresIn(jwtService.getExpirationTime())
                        .role(accounts.getRole().getRoleName())
                        .username(accounts.getUsername())
                        .build();
        return ResponseEntity.ok(ResponseDTO.builder()
                        .statusCode("200")
                        .message("User logged in successfully")
                        .data(loginResponse)
                        .build());
    }
}
