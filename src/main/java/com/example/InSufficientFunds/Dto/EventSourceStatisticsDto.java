package com.example.InSufficientFunds.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSourceStatisticsDto {

	private long requestsNotYetHandled;
	
	private long requestApproved;
	
	private long requestRejected;
	
}
