package com.example.InSufficientFunds.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.InSufficientFunds.Dto.StatusDto;
import com.example.InSufficientFunds.Service.StatusService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Status")
@CrossOrigin(origins = "http://localhost:4200")
public class StatusController {

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto) {

        logger.info("Inside StatusController - createStatus");

        StatusDto statusCreated = statusService.createStatus(statusDto);
        return new ResponseEntity<>(statusCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{statusId}")
    public ResponseEntity<StatusDto> updateStatus(@PathVariable Long statusId, @Valid @RequestBody StatusDto statusDto) {

        logger.info("Inside StatusController - updateStatus");

        StatusDto updatedStatus = statusService.updateStatus(statusDto, statusId);
        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);

    }
    
    @GetMapping("/{statusId}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long statusId) {

        logger.info("Inside StatusController - getStatusById");

        StatusDto statusDto = statusService.getStatusById(statusId);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<StatusDto>> getAllStatus() {

        logger.info("Inside StatusController - getAllStatus");

        List<StatusDto> statusDtoList = statusService.getAllStatus();
        return new ResponseEntity<>(statusDtoList, HttpStatus.OK);
    }

   
}
