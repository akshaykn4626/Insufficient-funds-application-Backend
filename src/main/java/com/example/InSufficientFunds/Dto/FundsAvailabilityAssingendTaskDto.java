package com.example.InSufficientFunds.Dto;

import java.time.LocalDateTime;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.util.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundsAvailabilityAssingendTaskDto extends Auditable<String>  {

	    @NotBlank(message = "CustomerNumber must not be null")
		private String customerNumber;
		
	    @NotBlank(message = "Application must not be null")
		private String application;
	    
	    @NotBlank(message = "CustomerName must not be null")
		private String customerName;
		
	    @NotBlank(message = "AccountNumber must not be null")
	    private String debitaccountNumber;
	    
	    @NotBlank(message = "TransactionCurrency must not be null")
		private String transactionCurrency;
	    
	    @NotNull(message = "TrasactionAmount must not be null")
		private Number trasactionAmount;
	    
	    @NotBlank(message = "AccountOfficer must not be null")
	    private String accountOfficer;
	  		
	  	@NotBlank(message = "AltAccountOfficer must not be null") 
	    private String altAccountOfficer; 
		
	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime dateMailSent;
		
	    @OneToOne
		private StatusDto status;
	    
	    private User user;
	    
	    private String assignedUserName;
}
