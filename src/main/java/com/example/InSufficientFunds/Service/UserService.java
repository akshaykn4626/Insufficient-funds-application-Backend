package com.example.InSufficientFunds.Service;

import java.util.List;

import com.example.InSufficientFunds.Dto.LoginDto;
import com.example.InSufficientFunds.Dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    LoginDto login(LoginDto loginDto);
}
