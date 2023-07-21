package com.example.InSufficientFunds.ServiceImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.InSufficientFunds.Dto.AddressDto;
import com.example.InSufficientFunds.Dto.LoginDto;
import com.example.InSufficientFunds.Dto.UserDto;
import com.example.InSufficientFunds.Entity.Address;
import com.example.InSufficientFunds.Entity.User;
import com.example.InSufficientFunds.Exceptions.ApiException;
import com.example.InSufficientFunds.Exceptions.ResourceNotFoundException;
import com.example.InSufficientFunds.Repository.AddressRepository;
import com.example.InSufficientFunds.Repository.UserRepository;
import com.example.InSufficientFunds.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

   

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
			AddressRepository addressRepository) {
		
    	this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.addressRepository = addressRepository;
	}

	@Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new ResourceNotFoundException("User already exists", "User Id", user.get().getId());
        }

        AddressDto addressDto = userDto.getAddress();
        Address address = modelMapper.map(addressDto, Address.class);
        Address addressCreated = addressRepository.save(address);

        User user1 =modelMapper.map(userDto, User.class);
        user1.setAddress(addressCreated);


        User savedUsers = userRepository.save(user1);
        return  modelMapper.map(savedUsers, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos =users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public LoginDto login(LoginDto loginDto) {

        String tempEmail = loginDto.getEmail();
        String tempPass = loginDto.getPassword();

        User user = new User();
        if (tempEmail != null && tempPass != null) {
            user = userRepository.findByEmailAndPassword(tempEmail, tempPass)
                    .orElseThrow(() -> new ApiException("Invalid username or password !!"));
        }
        return modelMapper.map(user, LoginDto.class);
    }


}
