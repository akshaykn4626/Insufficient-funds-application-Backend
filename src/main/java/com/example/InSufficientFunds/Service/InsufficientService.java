package com.example.InSufficientFunds.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.InSufficientFunds.Dto.EventSourceDto;
import com.example.InSufficientFunds.Dto.EventSourceStatisticsDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityAssingendTaskDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
import com.example.InSufficientFunds.Dto.FundsRequestProcessedDto;

public interface InsufficientService {

	List<FundsAvailabilityDto> getAllInSufficientFundsByStatus(String statusName);

	EventSourceDto createInSufficientFunds(@PathVariable String statusName,@PathVariable Long userId,@Valid EventSourceDto eventSourceDto);

	List<FundsRequestProcessedDto> getAllInSufficientFundsProcessesByday(String statusName);

	EventSourceStatisticsDto getCountReqNotYetHandledAndApprovedAndRejected();
	
	void assignRequestsToCurrentUser(List<Long> eventSourceId, Long userId, String statusName);

	EventSourceDto getEventSourceById(Long eventSourceId);

	List<FundsAvailabilityAssingendTaskDto> getAllInSufficientFundsByAssignTaskStatus(String statusName,Long userId);
	 
}
