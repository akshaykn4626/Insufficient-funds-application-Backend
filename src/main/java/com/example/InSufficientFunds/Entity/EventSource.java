package com.example.InSufficientFunds.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.InSufficientFunds.util.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSource  extends Auditable<String> {
	
    @NotBlank(message = "BusinessKey must not be null")
	private String businessKey;
    
    @NotBlank(message = "application must not be null")
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
	
    @NotBlank(message = "debitAccountCcy must not be null")
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
	
    @NotBlank(message = "discrepancyReason must not be null")
	private String discrepancyReason;
	
    @NotBlank(message = "CustomerNumber must not be null")
	private String customerNumber;
	
    @NotBlank(message = "CustomerName must not be null")
	private String customerName;
	
    @NotBlank(message = "accountOfficer must not be null")
	private String accountOfficer;
	
    @NotBlank(message = "altAccountOfficer must not be null")
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
    private Status status;
    
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    
    private LocalDateTime updatedOn;
    
    private LocalDateTime createdOn;
}
