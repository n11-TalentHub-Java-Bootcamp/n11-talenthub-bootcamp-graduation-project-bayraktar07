package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.enums.CreditLimitMultiplier;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CreditService {

    public CreditDto getCreditResultByUser(UserDto userDto){
    CreditResult creditResult = calculateCreditResult(userDto.getCreditScore());
    BigDecimal creditLimit = calculateCreditLimit(userDto.getCreditScore(), userDto.getMonthlyIncome(), userDto.getDeposit());
    return CreditDto.builder()
            .creditLimit(creditLimit)
            .creditResult(creditResult)
            .userDto(userDto)
            .build();
    }

    private CreditResult calculateCreditResult(Integer creditScore){
        if(creditScore < 500) {
            return CreditResult.DENIED;
        }
        return CreditResult.APPROVED;
    }

    private BigDecimal calculateCreditLimit(Integer creditScore, BigDecimal monthlyIncome, BigDecimal deposit) {
        int returnValue;
        int creditLimitMultiplier = CreditLimitMultiplier.MULTIPLIER.getValue();
        if(deposit == null) {
            deposit = new BigDecimal(0);
        }
        if(creditScore >= 1000) {
            returnValue = (monthlyIncome.intValue() * creditLimitMultiplier) + deposit.divide(new BigDecimal(2), RoundingMode.DOWN).intValue();
        }else if(monthlyIncome.intValue() >= 10000) {
            returnValue = (monthlyIncome.intValue() * creditLimitMultiplier / 2) + deposit.divide(new BigDecimal(4), RoundingMode.DOWN).intValue();
        }else if(monthlyIncome.intValue() > 5000) {
            returnValue = deposit.divide(new BigDecimal(5), RoundingMode.DOWN).intValue() + 20000;
        }else {
            returnValue = deposit.divide(new BigDecimal(10), RoundingMode.DOWN).intValue() + 10000;
        }
        return new BigDecimal(returnValue);
    }
}
