package com.smartdevelopers.OpenMarket.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSellerRequestDto
{
    private String name;
    private String mobNo;
    private String email;
    private String aadharNo;
}
