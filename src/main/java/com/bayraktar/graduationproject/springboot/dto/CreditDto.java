package com.bayraktar.graduationproject.springboot.dto;

import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CreditDto {

    private CreditResult creditResult;
    private BigDecimal creditLimit;
    private UserCreditDto userCreditDto;

}
