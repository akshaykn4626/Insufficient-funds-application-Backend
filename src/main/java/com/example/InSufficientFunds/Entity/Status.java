package com.example.InSufficientFunds.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.InSufficientFunds.util.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status extends Auditable<String> {
	
    @NotBlank(message = "Status name must not be null")
	private String name;

}
