package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponseDto
{
    private String customerName;
    private List<CardDto> cards;
}
