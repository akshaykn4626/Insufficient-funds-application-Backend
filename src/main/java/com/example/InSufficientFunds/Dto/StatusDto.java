package com.example.InSufficientFunds.Dto;

import javax.validation.constraints.NotBlank;

import com.example.InSufficientFunds.util.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto extends Auditable<String> {
	
    @NotBlank(message = "Status name must not be null")
	private String Name;

}
