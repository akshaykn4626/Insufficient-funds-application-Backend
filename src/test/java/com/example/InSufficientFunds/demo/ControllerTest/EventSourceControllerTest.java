//package com.example.InSufficientFunds.demo.ControllerTest;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import com.example.InSufficientFunds.Dto.AssignRequestDto;
//import com.example.InSufficientFunds.Dto.EventSourceDto;
//import com.example.InSufficientFunds.Dto.EventSourceStatisticsDto;
//import com.example.InSufficientFunds.Dto.FundsAvailabilityAssingendTaskDto;
//import com.example.InSufficientFunds.Dto.FundsAvailabilityDto;
//import com.example.InSufficientFunds.Service.EventSourceService;
//import com.example.InSufficientFunds.Service.InsufficientService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class EventSourceControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EventSourceService eventSourceService;
//    private InsufficientService insufficientService;
//
//    private EventSourceDto eventSourceDto;
//    private EventSourceStatisticsDto eventSourceStatisticsDto;
//    private FundsAvailabilityDto fundsAvailabilityDto;
//    private List<FundsAvailabilityAssingendTaskDto> fundsAvailabilityAssingendTaskDto;
//
//
//    @BeforeEach
//    void setUp() {
//        this.eventSourceDto = new EventSourceDto();
//        // add properties to the eventSourceDto
//
//        Mockito.when(insufficientService.createInSufficientFunds(Mockito.anyString(),Mockito.anyLong(),Mockito.any(EventSourceDto.class)))
//                .thenReturn(eventSourceDto);
//        
//        Mockito.when(insufficientService.getEventSourceById(Mockito.anyLong())).thenReturn(eventSourceDto);
//        Mockito.when(insufficientService.getCountReqNotYetHandledAndApprovedAndRejected()).thenReturn(eventSourceStatisticsDto);
//        Mockito.when(insufficientService.getAllInSufficientFundsByStatus(Mockito.anyString())).thenReturn((List<FundsAvailabilityDto>) (eventSourceDto));
//    }
//
//    @Test
//    public void shouldCreateEventSource() throws Exception {
//        String eventSourceDtoJson = new ObjectMapper().writeValueAsString(eventSourceDto);
//
//        ResultActions response = mockMvc.perform(post(
//                "/status/{statusName}/user/{userId}/InSufficient",
//                1, "status", 1)
//        		
//        		
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(eventSourceDtoJson));
//
//        response.andExpect(status().isCreated())
//                .andExpect(content().json(eventSourceDtoJson));
//    }
//
//    @Test
//    public void shouldGetEventSourceById() throws Exception {
//        String eventSourceDtoJson = new ObjectMapper().writeValueAsString(eventSourceDto);
//
//        ResultActions response = mockMvc.perform(get(
//                "/eventSource/{eventSourceId}", 1));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().json(eventSourceDtoJson));
//    }
//
//    @Test
//    public void shouldUpdateEventSources() throws Exception {
//        String eventSourceDtoJson = new ObjectMapper().writeValueAsString(eventSourceDto);
//
//        ResultActions response = mockMvc.perform(put(
//                "/status/{statusName}/user/{userId}/update-event-sources",
//                1, 1, 1, "status")
//         		
//        		
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(eventSourceDtoJson));
//
//        response.andExpect(status().isCreated())
//                .andExpect(content().json(eventSourceDtoJson));
//    }
//
//    @Test
//    public void getCountReqNotYetHandledAndApprovedAndRejected() throws Exception {
//        String eventSourceDtoJson = new ObjectMapper().writeValueAsString(Collections.singletonList(eventSourceDto));
//
//        ResultActions response = mockMvc.perform(get(
//                "/fundsavailability/count"));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().json(eventSourceDtoJson));
//    }
//
//    @Test
//    public void shouldGetAllInsufficientUnAssignedStatus() throws Exception {
//        String FundsAvailabilityDtoJson = new ObjectMapper().writeValueAsString((eventSourceDto));
//
//        ResultActions response = mockMvc.perform(get(
//                "/status/{statusname}", "status"));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().json(FundsAvailabilityDtoJson));
//    }
//
//    @Test
//    public void shouldAssignRequestsToCurrentUser() throws Exception {
//        AssignRequestDto assignRequestDto = new AssignRequestDto();
//        // add properties to the assignRequestDto
//        String assignRequestDtoJson = new ObjectMapper().writeValueAsString(assignRequestDto);
//
//        ResultActions response = mockMvc.perform(post(
//                "/requests/assign/user/{userId}/status/{statusName}", 1, "status")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(assignRequestDtoJson));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().string("Requests assigned successfully."));
//    }
//
//    @Test
//    public void shouldGetEventSourceStatusCount() throws Exception {
//        EventSourceDto eventSourceStatusDto = new EventSourceDto();
//        // add properties to the eventSourceStatusDto
//        String eventSourceStatusDtoJson = new ObjectMapper().writeValueAsString(eventSourceDto);
//
//        Mockito.when(insufficientService.getCountReqNotYetHandledAndApprovedAndRejected());
//
//        ResultActions response = mockMvc.perform(get(
//                "/eventSourceStatus/count"));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().json(eventSourceStatusDtoJson));
//    }
//
//    @Test
//    public void shouldGetAssignedEvents() throws Exception {
//        String eventSourceDtoJson = new ObjectMapper().writeValueAsString(Collections.singletonList(eventSourceDto));
//
//        Mockito.when(insufficientService.getAllInSufficientFundsByAssignTaskStatus(eventSourceDtoJson, null)).thenReturn((fundsAvailabilityAssingendTaskDto));
//
//        ResultActions response = mockMvc.perform(get(
//                "/requests/assigned"));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().json(eventSourceDtoJson));
//    }
//
//}