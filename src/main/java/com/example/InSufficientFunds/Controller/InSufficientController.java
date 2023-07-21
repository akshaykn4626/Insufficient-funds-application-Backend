package com.example.InSufficientFunds.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.InSufficientFunds.Dto.AssignRequestDto;
import com.example.InSufficientFunds.Dto.EventSourceDto;
import com.example.InSufficientFunds.Dto.EventSourceStatisticsDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityAssingendTaskDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
import com.example.InSufficientFunds.Dto.FundsRequestProcessedDto;
import com.example.InSufficientFunds.Service.InsufficientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/insufficient")
@CrossOrigin(origins = "http://localhost:4200")
public class InSufficientController {

    Logger logger = LoggerFactory.getLogger(InSufficientController.class);

    @Autowired
    private InsufficientService insufficientService;

   
    @GetMapping("/status/{statusName}")
    public ResponseEntity<List<FundsAvailabilityDto>> getAllInSufficientFundsByStatus(@PathVariable String statusName) {
        logger.info("Inside InSufficientController - getAllInSufficientFundsByStatus");

        List<FundsAvailabilityDto> fundsAvailabilityDto = insufficientService.getAllInSufficientFundsByStatus(statusName);

        return new ResponseEntity<>(fundsAvailabilityDto, HttpStatus.OK);
    }
    
    @GetMapping("/status/{statusName}/user/{userId}")
    public ResponseEntity<List<FundsAvailabilityAssingendTaskDto>> getAllInSufficientFundsByAssignTaskStatus(@PathVariable String statusName,@PathVariable Long userId) {
        logger.info("Inside InSufficientController - getAllInSufficientFundsByAssignTaskStatus");

        List<FundsAvailabilityAssingendTaskDto> fundsAvailabilityssingendTaskDto = insufficientService.getAllInSufficientFundsByAssignTaskStatus(statusName,userId);

        return new ResponseEntity<>(fundsAvailabilityssingendTaskDto, HttpStatus.OK);
    }
    
    
    @GetMapping("/eventSource/{eventSourceId}")
    public ResponseEntity<EventSourceDto> getEventSourceById(@PathVariable Long eventSourceId) {

        EventSourceDto eventSourceDto =insufficientService.getEventSourceById(eventSourceId);
        return new ResponseEntity<>(eventSourceDto, HttpStatus.OK);
    }

    @PostMapping("/status/{statusName}/user/{userId}/InSufficient")
    public ResponseEntity<EventSourceDto> createInSufficientFunds(@PathVariable String statusName,@PathVariable Long userId,@Valid @RequestBody EventSourceDto eventSourceDto){

        logger.info("Inside InSufficientController - createInSufficientFunds");

        EventSourceDto saveEventSource = insufficientService.createInSufficientFunds(statusName,userId,eventSourceDto);

        return new ResponseEntity<>(saveEventSource, HttpStatus.CREATED);

    }

    @GetMapping("/status/{statusName}/Processed")
    public ResponseEntity<List<FundsRequestProcessedDto>> getAllInSufficientFundsProcessesByday(@PathVariable String statusName) {
        logger.info("Inside InSufficientController - getAllInSufficientFundsProcessesByday");

        List<FundsRequestProcessedDto> fundsRequestProcessedDto = insufficientService.getAllInSufficientFundsProcessesByday(statusName);

        return new ResponseEntity<>(fundsRequestProcessedDto, HttpStatus.OK);
    }
    
	@GetMapping("/fundsavailability/count")
	public ResponseEntity<EventSourceStatisticsDto> getCountReqNotYetHandledAndApprovedAndRejected() {
		
		logger.info("Inside InSufficientController - getCountReqNotYetHandledAndApprovedAndRejected");
		
		EventSourceStatisticsDto CountReqNotYetHandledAndApprovedAndRejected = insufficientService.getCountReqNotYetHandledAndApprovedAndRejected();
		
		return new ResponseEntity<>(CountReqNotYetHandledAndApprovedAndRejected, HttpStatus.OK);
	}
	
	 @PostMapping("/requests/assign/user/{userId}/status/{statusName}")
	    public ResponseEntity<String> assignRequestsToCurrentUser(@RequestBody AssignRequestDto assignRequestDto, @PathVariable Long userId, @PathVariable String statusName) {

	        // Assign the selected requests to the current user
		 insufficientService.assignRequestsToCurrentUser(assignRequestDto.getEventSourceId(), userId, statusName);

	        return ResponseEntity.ok("Requests assigned successfully.");
	    }
    
    
}
