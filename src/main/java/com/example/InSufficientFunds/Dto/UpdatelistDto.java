package com.example.InSufficientFunds.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.example.InSufficientFunds.util.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatelistDto extends Auditable<String> {
	
	 @NotBlank(message = "account Number must not be null")
	 private String debitaccountNumber;
	 
	 private String accountDescription;
	 
	 private String accountOfficer;
	 
	 private UserDto user;
	 
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime now = LocalDateTime.now();
	 private LocalDateTime createdTime = now;
	
}
