package com.smartdevelopers.OpenMarket.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto  {
    private String productName;
    private Date orderDate;
    private int itemPrice;
    private int orderedQuantity;
    private int totalCost;
    private int deliveryCharge;
    private String cardUsedForPayment;
}
