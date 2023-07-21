package com.example.InSufficientFunds.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.InSufficientFunds.Dto.EventSourceDto;
import com.example.InSufficientFunds.Dto.EventSourceUpdateRequestDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
import com.example.InSufficientFunds.Dto.UpdatelistDto;
import com.example.InSufficientFunds.Entity.EventSource;
import com.example.InSufficientFunds.Entity.Status;
import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.Exceptions.ResourceNotFoundException;
import com.example.InSufficientFunds.Repository.EventSourceRepository;
import com.example.InSufficientFunds.Repository.StatusRepository;
import com.example.InSufficientFunds.Repository.UserRepository;
import com.example.InSufficientFunds.Service.EventSourceService;


@Service
@Transactional
public class EventSourceServiceImpl implements EventSourceService {

	@Autowired
	private EventSourceRepository eventSourceRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	
	
	public EventSourceServiceImpl(EventSourceRepository eventSourceRepository, StatusRepository statusRepository,
			UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.eventSourceRepository = eventSourceRepository;
		this.statusRepository = statusRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public void updateEventSources(List<Long> eventSourceIds, Long userId, String statusName) {

        List<EventSource> eventSources = eventSourceRepository.findByIdIn(eventSourceIds);

        if (!eventSources.isEmpty()) {

	        Status status = statusRepository.findByName(statusName);

	        if (status == null) {
	            throw new ResourceNotFoundException("Status", "Status Name", statusName);
	        }
	        
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

	        // Check if only one eventSourceId is provided
	        if (eventSourceIds.size() == 1) {
	            Long eventSourceId1 = eventSourceIds.get(0);
	            EventSource eventSource = eventSourceRepository.findById(eventSourceId1)
	                    .orElseThrow(() -> new ResourceNotFoundException("EventSource", "EventSource Id", eventSourceId1));

	            eventSource.setUser(user);
	            eventSource.setStatus(status);
	            LocalDateTime now = LocalDateTime.now();
	            eventSource.setUpdatedOn(now);

	            eventSourceRepository.save(eventSource);
	        } else {
	            // If multiple eventSourceIds are provided, retrieve all of them and set the user and status
	            List<EventSource> selectRequests = eventSourceRepository.findAllById(eventSourceIds);

	            for (EventSource request : selectRequests) {
	                request.setUser(user);
	                request.setStatus(status);
	                LocalDateTime now = LocalDateTime.now();
	                request.setUpdatedOn(now);
	            }

	            eventSourceRepository.saveAll(selectRequests);
	    }

	   
	}
	   
}


	@Override
	public List<UpdatelistDto> getAllInSufficientFundsByBatchAssignStatuslist(String statusName) {
		Status status = statusRepository.findByName(statusName);
		if (status != null) {
            List<EventSource> eventSources = eventSourceRepository.findAllByStatus(status);

            return eventSources.stream()
                    .map(eventSource -> modelMapper.map(eventSource, UpdatelistDto.class))
                    .collect(Collectors.toList());
        } else {
            // Return an empty list or handle the case when the status is not found
            return new ArrayList<>();
        }
	}
}



