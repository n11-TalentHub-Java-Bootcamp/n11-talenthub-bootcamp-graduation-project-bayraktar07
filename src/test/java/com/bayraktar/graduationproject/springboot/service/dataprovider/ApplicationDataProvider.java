package com.bayraktar.graduationproject.springboot.service.dataprovider;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApplicationDataProvider {


    public static ApplicationDto getApprovedApplicationDto() {
        CreditDto approvedCreditDto = CreditDataProvider.getApprovedResultAbove1000();
        return ApplicationDto.builder()
                .applicationDate(LocalDate.now())
                .creditLimit(approvedCreditDto.getCreditLimit())
                .creditResult(approvedCreditDto.getCreditResult())
                .userId(1L)
                .build();
    }

    public static ApplicationDto getApprovedApplicationDtoNew() {
        CreditDto approvedCreditDto = CreditDataProvider.getApprovedResultAbove1000New();
        return ApplicationDto.builder()
                .applicationDate(LocalDate.now())
                .creditLimit(approvedCreditDto.getCreditLimit())
                .creditResult(approvedCreditDto.getCreditResult())
                .userId(3L)
                .build();
    }
}
