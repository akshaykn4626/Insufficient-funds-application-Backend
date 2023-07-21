package com.example.InSufficientFunds.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.InSufficientFunds.Dto.EventSourceDto;
import com.example.InSufficientFunds.Dto.EventSourceStatisticsDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityAssingendTaskDto;
import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
import com.example.InSufficientFunds.Dto.FundsRequestProcessedDto;
import com.example.InSufficientFunds.Entity.EventSource;
import com.example.InSufficientFunds.Entity.Status;
import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.Exceptions.ResourceNotFoundException;
import com.example.InSufficientFunds.Repository.EventSourceRepository;
import com.example.InSufficientFunds.Repository.StatusRepository;
import com.example.InSufficientFunds.Repository.UserRepository;
import com.example.InSufficientFunds.Service.InsufficientService;

@Service
@Transactional
public class InsufficientServiceImpl implements InsufficientService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EventSourceRepository eventSourceRepository;

	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	
	public InsufficientServiceImpl(ModelMapper modelMapper, EventSourceRepository eventSourceRepository,
			StatusRepository statusRepository,UserRepository userRepository) {
		
		this.modelMapper = modelMapper;
		this.eventSourceRepository = eventSourceRepository;
		this.statusRepository = statusRepository;
		this.userRepository = userRepository;
	}
	
	@Override
    public EventSourceDto getEventSourceById(Long eventSourceId) {

        EventSource eventSource = eventSourceRepository.findById(eventSourceId)
                .orElseThrow(() -> new ResourceNotFoundException("EventSource", "EventSource Id", eventSourceId));

        return modelMapper.map(eventSource, EventSourceDto.class);
    }


	@Override
	public List<FundsAvailabilityDto> getAllInSufficientFundsByStatus(String statusName) {

		Status status = statusRepository.findByName(statusName);
		if (status != null) {
            List<EventSource> eventSources = eventSourceRepository.findAllByStatus(status);

            return eventSources.stream()
                    .map(eventSource -> modelMapper.map(eventSource, FundsAvailabilityDto.class))
                    .collect(Collectors.toList());
        } else {
            // Return an empty list or handle the case when the status is not found
            return new ArrayList<>();
        }
  
}

	@Override
	public EventSourceDto createInSufficientFunds(String statusName, Long userId, @Valid EventSourceDto eventSourceDto) {
        Status status = statusRepository.findByName(statusName);
		
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));
		if (status != null) {
		
		EventSource eventSource = modelMapper.map(eventSourceDto, EventSource.class);
		eventSource.setStatus(status);
		eventSource.setUser(user);
		LocalDateTime now = LocalDateTime.now();
        eventSource.setUpdatedOn(now);
		
		EventSource savedEventSource =eventSourceRepository.save(eventSource);
		
		return modelMapper.map(savedEventSource, EventSourceDto.class);
	}else {
        // Handle the case when the status name is not found
        throw new ResourceNotFoundException("Status", "Status Name", statusName);
    }

}

	@Override
	public List<FundsRequestProcessedDto> getAllInSufficientFundsProcessesByday(String statusName) {
		Status status = statusRepository.findByName(statusName);
		if (status != null) {
            List<EventSource> eventSources = eventSourceRepository.findAllByStatus(status);

            return eventSources.stream()
                    .map(eventSource -> modelMapper.map(eventSource, FundsRequestProcessedDto.class))
                    .collect(Collectors.toList());
        } else {
            // Return an empty list or handle the case when the status is not found
            return new ArrayList<>();
        }
    
}
	
	
	 @Override
	    public void assignRequestsToCurrentUser(List<Long> eventSourceId, Long userId,String statusName) {

	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

	        Status status = statusRepository.findByName(statusName);
              
	        if (status == null) {
	            throw new ResourceNotFoundException("Status", "Status Name", statusName);
	        }
	        
	        // Check if only one eventSourceId is provided
	        if (eventSourceId.size() == 1) {
	            Long eventSourceIds = eventSourceId.get(0);
	            EventSource eventSource = eventSourceRepository.findById(eventSourceIds)
	                    .orElseThrow(() -> new ResourceNotFoundException("EventSource", "EventSource Id", eventSourceIds));

	            eventSource.setUser(user);
	            eventSource.setStatus(status);
	            

	            eventSourceRepository.save(eventSource);
	        } else {
	            // If multiple eventSourceIds are provided, retrieve all of them and set the user and status
	            List<EventSource> selectRequests = eventSourceRepository.findAllById(eventSourceId);

	            for (EventSource request : selectRequests) {
	                request.setUser(user);
	                request.setStatus(status);
	            }

	            eventSourceRepository.saveAll(selectRequests);
	    }
	    }
	

	@Override
	public EventSourceStatisticsDto getCountReqNotYetHandledAndApprovedAndRejected() {
		EventSourceStatisticsDto eventSourceStatisticsDto = new EventSourceStatisticsDto();		
		eventSourceStatisticsDto.setRequestsNotYetHandled(getRequestsNotYetHandled());
		eventSourceStatisticsDto.setRequestApproved(getRequestsApproved());
		eventSourceStatisticsDto.setRequestRejected(getRequestsRejected());
		
		return eventSourceStatisticsDto;
	}

	private long getRequestsNotYetHandled() {
		
		return eventSourceRepository.findAllByVerified();
			
	}
	
	private long getRequestsApproved() {
		
		return eventSourceRepository.findAllByApproved();
	}
	
	private long getRequestsRejected() {
		
		return eventSourceRepository.findAllByRejected();
	}

	@Override
	public List<FundsAvailabilityAssingendTaskDto> getAllInSufficientFundsByAssignTaskStatus(String statusName, Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

	    Status status = statusRepository.findByName(statusName);
	    if (status != null) {
	        List<EventSource> eventSources = eventSourceRepository.findAllByStatus(status);

	        return eventSources.stream()
	                .map(eventSource -> {
	                    FundsAvailabilityAssingendTaskDto dto = modelMapper.map(eventSource, FundsAvailabilityAssingendTaskDto.class);
	                    dto.setAssignedUserName(user.getUserName());
	                    return dto;
	                })
	                .collect(Collectors.toList());
	    } else {
	        // Return an empty list or handle the case when the status is not found
	        return new ArrayList<>();
	    }
	}


	
	
}
