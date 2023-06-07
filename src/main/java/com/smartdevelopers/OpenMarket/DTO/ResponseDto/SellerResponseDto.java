package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto
{
    private String name;

    private String email;

    private String mobNo;

}
