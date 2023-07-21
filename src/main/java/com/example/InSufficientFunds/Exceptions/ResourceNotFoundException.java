package com.example.InSufficientFunds.Exceptions;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;
	
	String resourceName2;
	String fieldName2;
	List<Long> eventSourceIds;
	
	String resourceName3;
	String fieldName3;
	String statusName3;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName) {
		super(String.format("%s", resourceName));
		this.resourceName = resourceName;
	}

	public ResourceNotFoundException(String resourceName2, String fieldName2, List<Long> eventSourceIds) {
		super(String.format("%s not found with %s : %s", resourceName2, fieldName2, eventSourceIds));
		this.resourceName2 = resourceName2;
		this.fieldName2 = fieldName2;
		this.eventSourceIds = eventSourceIds;
		
	}

	public ResourceNotFoundException(String resourceName3, String fieldName3, String statusName3) {
		super(String.format("%s not found with %s : %s", resourceName3, fieldName3, statusName3));
		this.resourceName3 = resourceName3;
		this.fieldName3 = fieldName3;
		this.statusName3 = statusName3;
	}

}
