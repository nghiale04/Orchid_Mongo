package com.sum25.orchids.services.Impl;

import com.sum25.orchids.dto.LoginDto;
import com.sum25.orchids.dto.RegisterUserDto;
import com.sum25.orchids.entity.Accounts;
import com.sum25.orchids.entity.Role;
import com.sum25.orchids.exception.AccountAlreadyExistsException;
import com.sum25.orchids.repository.AccountRepository;
import com.sum25.orchids.repository.RoleRepository;
import com.sum25.orchids.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Accounts signUp(RegisterUserDto registerUserDto) {
        Optional<Accounts> existingAccount = accountRepository.findByEmail(registerUserDto.getEmail());
        if(existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists with email: " + registerUserDto.getEmail());
        }
        Role roleName = roleRepository.findRoleById("685cf7e11f794238a314da13");
        Accounts accounts = Accounts.builder()
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .accountName(registerUserDto.getAccountName())
                .role(roleName)
                .build();
        return accountRepository.save(accounts);
    }

    @Override
    public Accounts authenticate(LoginDto loginDto) {
        Accounts accounts = accountRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Wrong email or password!"));

        if (!passwordEncoder.matches(loginDto.getPassword(), accounts.getPassword())) {
            throw new RuntimeException("Wrong email or password!");
        }

        // Create an authentication token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accounts.getEmail(), loginDto.getPassword());

        return accounts;
    }
}
