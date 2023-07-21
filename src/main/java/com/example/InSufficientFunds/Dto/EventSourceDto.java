package com.example.InSufficientFunds.Dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.InSufficientFunds.Entity.Status;
import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.util.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSourceDto  extends Auditable<String> {
	
    @NotBlank(message = "BusinessKey must not be null")
	private String businessKey;
    
	private String application;
	
    @NotBlank(message = "Comments must not be null")
	private String comments;
	
    @NotBlank(message = "TransactionCurrency must not be null")
	private String transactionCurrency;
	
    @NotNull(message = "TrasactionAmount must not be null")
	private double trasactionAmount;
	
    @NotNull(message = "AmountInMur must not be null")
	private double amountInMur;
	
    @NotBlank(message = "DebitAccountNumber must not be null")
	private String debitAccountNumber;
	
    @NotBlank(message = "AccountShortName must not be null")
	private String accountShortName;
	
	private String debitAccountCcy;
	
    @NotBlank(message = "PaymentDetails1 must not be null")
	private String paymentDetails1;
	
    @NotBlank(message = "PaymentDetails2 must not be null")
	private String paymentDetails2;
	
    @NotBlank(message = "PaymentDetails3 must not be null")
	private String paymentDetails3;
	
    @NotBlank(message = "PaymentDetails4 must not be null")
	private String paymentDetails4;
	
    @NotBlank(message = "Verified must not be null")
	private String verified;
	
	private String discrepancyReason;
	
    @NotBlank(message = "CustomerNumber must not be null")
	private String customerNumber;
	
    @NotBlank(message = "CustomerName must not be null")
	private String customerName;
	
	private String accountOfficer;
	
	private String altAccountOfficer;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateMailSent;
	
    @NotBlank(message = "Override must not be null") 
	private String override;
	
    @NotBlank(message = "BuName must not be null") 
	private String buName;
	
    @NotNull(message = "AmountInDebitAccountCcy must not be null") 
	private double amountInDebitAccountCcy;
	
    @NotNull(message = "DebitAccountBalance must not be null") 
	private double debitAccountBalance;
	
    @NotBlank(message = "BeneficiaryAccountNumber must not be null") 
	private String beneficiaryAccountNumber;
	
    @NotBlank(message = "BeneficiaryName must not be null") 
	private String beneficiaryName;
	
    @NotBlank(message = "BeneficiaryBankName must not be null") 
	private String beneficiaryBankName;
	
    @NotBlank(message = "BeneficiaryBankSwiftCode must not be null") 
	private String beneficiaryBankSwiftCode;
	
    @NotNull(message = "AggredRate must not be null") 
	private double aggredRate;
	
    @OneToOne(cascade = CascadeType.ALL)
	private StatusDto status;
    
    @OneToOne(cascade = CascadeType.ALL)
    private UserDto user;
    
    private LocalDateTime updatedOn;
    
}
