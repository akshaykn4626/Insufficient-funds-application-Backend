package com.example.InSufficientFunds.Service;

import java.util.List;

import com.example.InSufficientFunds.Dto.EventSourceUpdateRequestDto;
import com.example.InSufficientFunds.Dto.UpdatelistDto;

public interface EventSourceService {

	void updateEventSources(List<Long> eventSourceIds, Long userId, String statusName);

	List<UpdatelistDto> getAllInSufficientFundsByBatchAssignStatuslist(String statusName);

	
	

}
