package com.sum25.orchids.services;

import com.sum25.orchids.dto.LoginDto;
import com.sum25.orchids.dto.RegisterUserDto;
import com.sum25.orchids.entity.Accounts;

public interface AuthenticationService {
    Accounts signUp(RegisterUserDto registerUserDto);
    Accounts authenticate(LoginDto loginDto);


}
