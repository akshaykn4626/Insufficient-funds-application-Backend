package com.example.InSufficientFunds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class InSufficientFundsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InSufficientFundsApplication.class, args);
	}
	
	 @Bean
	    public ModelMapper modelMapper() {

	         ModelMapper modelMapper=new ModelMapper();
	         modelMapper.getConfiguration().setAmbiguityIgnored(true);

	         return modelMapper;
	    }

}
