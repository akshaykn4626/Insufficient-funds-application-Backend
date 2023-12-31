package com.example.InSufficientFunds.Entity;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.InSufficientFunds.util.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Auditable<String> {

    @NotBlank(message = "Please provide address Line 1")
    @Size(min = 3, message = "address line 1 must be atleast 10 characters")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Please provide city")
    private String city;

    @NotBlank(message = "Please provide pinCode")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Please provide US pinCode")
    private String pinCode;

    @NotBlank(message = "Please provide state")
    private String state;
}
