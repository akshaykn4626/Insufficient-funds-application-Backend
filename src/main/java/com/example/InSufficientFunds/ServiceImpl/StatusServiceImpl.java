package com.example.InSufficientFunds.ServiceImpl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InSufficientFunds.Dto.StatusDto;
import com.example.InSufficientFunds.Entity.Status;
import com.example.InSufficientFunds.Exceptions.ResourceNotFoundException;
import com.example.InSufficientFunds.Repository.StatusRepository;
import com.example.InSufficientFunds.Service.StatusService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl  implements StatusService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StatusRepository statusRepository;

   
    
    public StatusServiceImpl(ModelMapper modelMapper, StatusRepository statusRepository) {
		
    	this.modelMapper = modelMapper;
		this.statusRepository = statusRepository;
	}


	@Override
    public StatusDto createStatus(StatusDto statusDto) {

    	Status status = modelMapper.map(statusDto, Status.class);

        Status savedStatus = statusRepository.save(status);
        return  modelMapper.map(savedStatus, StatusDto.class);
    }


    @Override
    public StatusDto updateStatus(StatusDto statusDto, Long statusId) {

    	Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status ", "Status Id", statusId));

    	status.setName(statusDto.getName());
    	Status statusUpdated = statusRepository.save(status);

        return modelMapper.map(statusUpdated, StatusDto.class);
    }

   
    @Override
    public StatusDto getStatusById(Long statusId) {

    	Optional<Status> status = statusRepository.findById(statusId);
        if (status != null) {
            return modelMapper.map(status, StatusDto.class);
        }
        throw new ResourceNotFoundException("Status ", "Status Id", statusId);
    }

    @Override
    public List<StatusDto> getAllStatus() {

        List<Status> statusList = statusRepository.findAll();
        List<StatusDto> statusDtoList = statusList.stream().map(Status ->
                modelMapper.map(Status, StatusDto.class)).collect(Collectors.toList());
        return statusDtoList;
    }
}
