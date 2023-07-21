package com.example.InSufficientFunds.Dto;

import java.util.List;

import com.example.InSufficientFunds.Dto.EventSourceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSourceUpdateRequestDto {

    private List<Long> eventSourceIds;

}
