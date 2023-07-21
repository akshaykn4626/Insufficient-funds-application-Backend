package com.example.InSufficientFunds.Dto;

import com.example.InSufficientFunds.util.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto extends Auditable<String> {

    private String email;

    private String password;
}
