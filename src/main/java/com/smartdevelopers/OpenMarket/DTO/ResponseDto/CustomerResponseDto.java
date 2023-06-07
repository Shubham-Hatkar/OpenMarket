package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto
{
    private String name;
    private String email;
    private String mobNo;
    private int age;
}
