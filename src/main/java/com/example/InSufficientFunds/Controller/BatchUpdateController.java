package com.example.InSufficientFunds.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InSufficientFunds.Dto.EventSourceUpdateRequestDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
import com.example.InSufficientFunds.Dto.UpdatelistDto;
import com.example.InSufficientFunds.Service.EventSourceService;

@RestController
@RequestMapping("/api/batch")
@CrossOrigin(origins = "http://localhost:4200")
public class BatchUpdateController {
	
	 Logger logger = LoggerFactory.getLogger(BatchUpdateController.class);

	@Autowired
    private EventSourceService eventSourceService;
  

    
    @PutMapping("/status/{statusName}/user/{userId}/update-event-sources")
    public ResponseEntity<String> updateEventSources(@RequestBody EventSourceUpdateRequestDto updateRequestDto, @PathVariable Long userId, @PathVariable String statusName) {

        logger.info("Inside BatchUpdateController - getAllInSufficientFundsByAssignStatuslist");

     	 eventSourceService.updateEventSources(updateRequestDto.getEventSourceIds(),userId, statusName);
     	 
    	 return ResponseEntity.ok("Requests Updated  successfully.");
    }
    
    
    @GetMapping("/status/{statusName}/BatchAssignStatuslist")
    public ResponseEntity<List<UpdatelistDto>> getAllInSufficientFundsByBatchAssignStatuslist(@PathVariable String statusName) {
       
    	logger.info("Inside BatchUpdateController - getAllInSufficientFundsByBatchAssignStatuslist");

        List<UpdatelistDto> fundsAvailabilityDto = eventSourceService.getAllInSufficientFundsByBatchAssignStatuslist(statusName);

        return new ResponseEntity<>(fundsAvailabilityDto, HttpStatus.OK);
    }


}
