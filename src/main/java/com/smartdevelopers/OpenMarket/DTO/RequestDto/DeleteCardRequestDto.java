package com.smartdevelopers.OpenMarket.DTO.RequestDto;

import com.smartdevelopers.OpenMarket.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCardRequestDto
{
    private int customerId;
    private CardType cardType;
}
