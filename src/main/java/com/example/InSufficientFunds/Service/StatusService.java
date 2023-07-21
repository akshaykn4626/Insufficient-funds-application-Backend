package com.example.InSufficientFunds.Service;

import java.util.List;

import javax.validation.Valid;

import com.example.InSufficientFunds.Dto.StatusDto;

public interface StatusService {

	StatusDto createStatus(StatusDto statusDto);

	StatusDto updateStatus(@Valid StatusDto statusDto, Long statusId);

	List<StatusDto> getAllStatus();

	StatusDto getStatusById(Long statusId);
	
}
