package com.example.InSufficientFunds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InSufficientFunds.Dto.LoginDto;
import com.example.InSufficientFunds.Dto.UserDto;
import com.example.InSufficientFunds.Service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/User")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginDto> login(@Valid @RequestBody LoginDto loginDto) {

		LoginDto loggedInUser = userService.login(loginDto);
		return ResponseEntity.ok(loggedInUser);

	}


}
