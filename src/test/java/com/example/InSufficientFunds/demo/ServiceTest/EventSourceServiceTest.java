//package com.example.InSufficientFunds.demo.ServiceTest;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//import com.example.InSufficientFunds.Dto.EventSourceDto;
//import com.example.InSufficientFunds.Dto.EventSourceStatisticsDto;
//import com.example.InSufficientFunds.Dto.FundsAvailabilityAssingendTaskDto;
//import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
//import com.example.InSufficientFunds.Entity.EventSource;
//import com.example.InSufficientFunds.Entity.Status;
//import com.example.InSufficientFunds.Entity.User;
//import com.example.InSufficientFunds.Exceptions.ResourceNotFoundException;
//import com.example.InSufficientFunds.Repository.EventSourceRepository;
//import com.example.InSufficientFunds.Repository.StatusRepository;
//import com.example.InSufficientFunds.Repository.UserRepository;
//import com.example.InSufficientFunds.ServiceImpl.EventSourceServiceImpl;
//import com.example.InSufficientFunds.ServiceImpl.InsufficientServiceImpl;
//
//import java.util.Optional;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class EventSourceServiceTest {
//
//    @Mock
//    private EventSourceRepository eventSourceRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private StatusRepository statusRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private EventSourceServiceImpl eventSourceService;
//    
//    @InjectMocks
//    private InsufficientServiceImpl insufficientService;
//
//    @Mock
//    private EventSourceDto eventSourceDto;
//    
//    @Mock
//    private EventSource eventSource;
//    @Mock
//    private Status status;
//    @Mock
//    private User user;
//   
//
//    @BeforeEach
//    public void setUp() {
//        status = new Status();
//        status.setName("testStatus");
//
//        user = new User();
//        user.setUserName("testUser");
//        user.setPassword("testPassword");
//    
//        eventSourceDto = new EventSourceDto();
//        eventSourceDto.setBusinessKey("businessKey1");
//        eventSourceDto.setApplication("A2K");
//        eventSourceDto.setComments("comment1");
//        eventSourceDto.setTransactionCurrency("USD");
//        eventSourceDto.setTrasactionAmount(600.0);
//        eventSourceDto.setAmountInMur(200.0);
//        eventSourceDto.setDebitAccountNumber("1234567890");
//        eventSourceDto.setAccountShortName("Saving");
//        eventSourceDto.setDebitAccountCcy("EUR");
//        eventSourceDto.setPaymentDetails1("abc");
//        eventSourceDto.setPaymentDetails2("jhi");
//        eventSourceDto.setPaymentDetails3("klm");
//        eventSourceDto.setPaymentDetails4("def");
//        eventSourceDto.setVerified("yes");
//        eventSourceDto.setDiscrepancyReason("Not Sufficient");
//        eventSourceDto.setCreatedBy("akshay");
//        eventSourceDto.setCreationDate(user.getCreationDate());
//        eventSourceDto.setLastModifiedBy("akshay");
//        eventSourceDto.setUpdatedOn(user.getLastModifiedDate());
//        eventSourceDto.setCustomerNumber("1234456");
//        eventSourceDto.setCustomerName("akshay");
//        eventSourceDto.setAccountOfficer("abc");
//        eventSourceDto.setAltAccountOfficer("def");
//        eventSourceDto.setOverride("yes");
//        eventSourceDto.setBuName("rajesh");
//        eventSourceDto.setAmountInDebitAccountCcy(311191);
//        eventSourceDto.setDebitAccountBalance(400000);
//        eventSourceDto.setBeneficiaryAccountNumber("000446534634");
//        eventSourceDto.setBeneficiaryName("Rajesh");
//        eventSourceDto.setBeneficiaryBankName("SBI");
//        eventSourceDto.setBeneficiaryBankSwiftCode("4465");
//        eventSourceDto.setAggredRate(10);
//        
//        eventSource = new EventSource();
//        eventSource.setBusinessKey(eventSourceDto.getBusinessKey());
//        eventSource.setApplication(eventSourceDto.getApplication());
//        eventSource.setComments(eventSourceDto.getComments());
//        eventSource.setTransactionCurrency(eventSourceDto.getTransactionCurrency());
//        eventSource.setTrasactionAmount(eventSourceDto.getTrasactionAmount());
//        eventSource.setAmountInMur(eventSourceDto.getAmountInMur());
//        eventSource.setDebitAccountNumber(eventSourceDto.getDebitAccountNumber());
//        eventSource.setAccountShortName(eventSourceDto.getAccountShortName());
//        eventSource.setDebitAccountCcy(eventSourceDto.getDebitAccountCcy());
//        eventSource.setPaymentDetails1(eventSourceDto.getPaymentDetails1());
//        eventSource.setPaymentDetails2(eventSourceDto.getPaymentDetails2());
//        eventSource.setPaymentDetails3(eventSourceDto.getPaymentDetails3());
//        eventSource.setPaymentDetails4(eventSourceDto.getPaymentDetails4());
//        eventSource.setVerified(eventSourceDto.getVerified());
//        eventSource.setDiscrepancyReason(eventSourceDto.getDiscrepancyReason());
//        eventSource.setCreatedBy(eventSourceDto.getCreatedBy());
//        eventSource.setCreatedOn(eventSourceDto.getCreationDate());
//        eventSource.setLastModifiedBy(eventSourceDto.getLastModifiedBy());
//        eventSource.setUpdatedOn(eventSourceDto.getLastModifiedDate());
//        eventSource.setCustomerNumber(eventSourceDto.getCustomerName());
//        eventSource.setCustomerName(eventSourceDto.getCustomerName());
//        eventSource.setAccountOfficer(eventSourceDto.getAccountOfficer());
//        eventSource.setAltAccountOfficer(eventSourceDto.getAltAccountOfficer());
//        eventSource.setOverride(eventSourceDto.getOverride());
//        eventSource.setBuName(eventSourceDto.getBuName());
//        eventSource.setAmountInDebitAccountCcy(eventSourceDto.getAmountInDebitAccountCcy());
//        eventSource.setDebitAccountBalance(eventSourceDto.getDebitAccountBalance());
//        eventSource.setBeneficiaryAccountNumber(eventSourceDto.getBeneficiaryAccountNumber());
//        eventSource.setBeneficiaryName(eventSourceDto.getBeneficiaryName());
//        eventSource.setBeneficiaryBankName(eventSourceDto.getBeneficiaryBankName());
//        eventSource.setBeneficiaryBankSwiftCode(eventSourceDto.getBeneficiaryBankSwiftCode());
//        eventSource.setAggredRate(eventSourceDto.getAggredRate());
//        eventSource.setStatus(status);
//        eventSource.setUser(user);
//
//    }
//
//    @Test
//    @DisplayName("Create Event Source test")
//    public void shouldCreateEventSource() {
//        String statusName = "unassigned";
//        Status status = new Status(); // Create a dummy Status object for testing
//        status.setId(1L); // Set other properties as needed
//
//        // Mock the behavior of the repositories to return the dummy objects
//        when(statusRepository.findByName(statusName)).thenReturn(status);
//        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
//        when(eventSourceRepository.save(any(EventSource.class))).thenAnswer(invocation -> {
//            EventSource eventSource = invocation.getArgument(0);
//            if (eventSource == null) {
//                throw new RuntimeException("EventSource is null");
//            }
//            // Perform other save operations if needed
//            return eventSource;
//        });
//
//        EventSourceDto returnedEventSource = null;
//        try {
//            returnedEventSource = insufficientService.createInSufficientFunds(statusName, 9L, eventSourceDto);
//        } catch (RuntimeException e) {
//            // Handle the exception here (e.g., log the error or fail the test)
//            fail("Failed to create EventSource: " + e.getMessage());
//        }
//
//        // Assert that the returnedEventSource is not null and contains the expected values
//        assertNotNull(returnedEventSource);
//        // Add more assertions here to check the properties of the returnedEventSource
//
//        // Verify that the statusRepository.findByName, userRepository.findById, and eventSourceRepository.save methods were called
//        verify(statusRepository, times(1)).findByName(anyString());
//        verify(userRepository, times(1)).findById(anyLong());
//        verify(eventSourceRepository, times(1)).save(any(EventSource.class));
//    }
//
//
//
//
//    @Test
//    @DisplayName("Get Event Source by Id test")
//    public void shouldGetEventSourceById() {
//        // when - action and behaviour that we are going to test
//        when(eventSourceRepository.findById(anyLong())).thenReturn(Optional.of(eventSource));
//        EventSourceDto expectedEventSourceDto = new EventSourceDto();  
//        when(modelMapper.map(any(EventSource.class), eq(EventSourceDto.class))).thenReturn(expectedEventSourceDto);
//
//        EventSourceDto returnedEventSourceDto = insufficientService.getEventSourceById(1L);
//
//        // then - verify the result and output using assert statements
//        assertThat(returnedEventSourceDto).isNotNull();
//        assertThat(returnedEventSourceDto).isEqualTo(expectedEventSourceDto);
//        verify(eventSourceRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    @DisplayName("Update Event Source test")
//    public void shouldUpdateEventSource() {
//    	
//    	 List<Long> eventSourceIds = Arrays.asList(1L, 2L, 3L); // Example eventSourceIds
//    	    EventSource eventSource1 = new EventSource(); // Create dummy EventSource objects for testing
//    	    eventSource1.setId(1L); // Set other properties as needed
//    	    EventSource eventSource2 = new EventSource();
//    	    eventSource2.setId(2L);
//    	    
//    	    List<EventSource> eventSources = Arrays.asList(eventSource1, eventSource2); // Create a list of EventSource objects
//
//    	
//        // when - action and behaviour that we are going to test
//        when(eventSourceRepository.findByIdIn(anyList())).thenReturn(eventSources);
//        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
//        when(statusRepository.findByName(anyString())).thenReturn((status));
//
//       eventSourceService.updateEventSources(Arrays.asList(17L, 18L, 19L), 9L, "proceed");
//        
//       
//        verify(userRepository, times(1)).findById(anyLong());
//        verify(statusRepository, times(1)).findByName(anyString());
//       
//
//    }
//
//    @Test
//    @DisplayName("Get All Event Source data test")
//    public void shouldGetAllEventSourceData() {
//        // when - action and behaviour that we are going to test
//        when(eventSourceRepository.findAll()).thenReturn(Collections.singletonList(eventSource));
//        EventSourceStatisticsDto returnedEventSourceDtoList = insufficientService.getCountReqNotYetHandledAndApprovedAndRejected();
//
//        // then - verify the result and output using assert statements
//        assertThat(returnedEventSourceDtoList).isNull();
//       
//    }
//
//    @Test
//    @DisplayName("Get all Event Source data by unassigned status test")
//    public void shouldGetAllEventSourceDataByUnassignedStatus() {
//        // when - action and behaviour that we are going to test
//        when(eventSourceRepository.findByStatusName(anyString())).thenReturn(Collections.singletonList(eventSource));
//        List<FundsAvailabilityDto> returnedEventSourceDtoList = insufficientService.getAllInSufficientFundsByStatus("Unassigned");
//
//        // then - verify the result and output using assert statements
//        assertThat(returnedEventSourceDtoList).isNotNull();
//        verify(eventSourceRepository, times(1)).findByStatusName(anyString());
//    }
//
//    @Test
//    @DisplayName("Get All InSufficient Funds By Assign Task Status - Status Not Found")
//    public void shouldThrowResourceNotFoundExceptionWhenStatusNotFound() {
//        String statusName = "Assign";
//        Long userId = 9L;
//
//        // Mock the behavior of the repositories to return null for the status
//        when(statusRepository.findByName(statusName)).thenReturn(null);
//
//        // Assert that the service method throws a ResourceNotFoundException
//        assertThrows(ResourceNotFoundException.class, () -> {
//            insufficientService.getAllInSufficientFundsByAssignTaskStatus(statusName, userId);
//        });
//
//        // Verify that the statusRepository.findByName method was called
//        verify(statusRepository, times(1)).findByName(statusName);
//    }
//
//
//    @Test
//    @DisplayName("Get Event Source Status Count test")
//    public void shouldGetEventSourceStatusCount() {
//        // when - action and behaviour that we are going to test
//        when(eventSourceRepository.countByStatusName("Unassigned")).thenReturn(10L);
//        when(eventSourceRepository.countByStatusName("Proceed")).thenReturn(20L);
//        when(eventSourceRepository.countByStatusName("Reject")).thenReturn(30L);
//        
//        EventSourceStatisticsDto returnedEventSourceStatusDto = insufficientService.getCountReqNotYetHandledAndApprovedAndRejected();
//
//        // then - verify the result and output using assert statements
//        assertThat(returnedEventSourceStatusDto).isNotNull();
//        assertThat(returnedEventSourceStatusDto.getRequestsNotYetHandled()).isEqualTo(10L);
//        assertThat(returnedEventSourceStatusDto.getRequestApproved()).isEqualTo(20L);
//        assertThat(returnedEventSourceStatusDto.getRequestRejected()).isEqualTo(30L);
//    }
//
//    @Test
//    @DisplayName("Assign Requests to Current Users test")
//    public void shouldAssignRequestsToCurrentUsers() {
//        // when - action and behaviour that we are going to test
//        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
//        when(statusRepository.findByName(anyString())).thenReturn((status));
//        when(eventSourceRepository.findById(anyLong())).thenReturn(Optional.of(eventSource));
//
//        insufficientService.assignRequestsToCurrentUser(Arrays.asList(1L, 2L, 3L), 1L, "Assign");
//
//        // then - verify the result and output using assert statements
//        verify(userRepository, times(1)).findById(anyLong());
//        verify(statusRepository, times(1)).findByName(anyString());
//        verify(eventSourceRepository, times(3)).findById(anyLong());
//        verify(eventSourceRepository, times(3)).save(any(EventSource.class));
//    }
//
//}
