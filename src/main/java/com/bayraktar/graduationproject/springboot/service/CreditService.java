package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.enums.CreditLimitMultiplier;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final UserEntityService userEntityService;

    public CreditDto getCreditResultByUser(Long id){
        User user = userEntityService.findUserById(id);
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userToUserCreditDto(user);

        if(user.getCreditScore() == null) {
            user.setCreditScore(calculateAndSaveCreditScoreByUserIdentificationNumber(user));
        }

    CreditResult creditResult = calculateCreditResult(user.getCreditScore());
    BigDecimal creditLimit = calculateCreditLimit(user.getCreditScore(), user.getMonthlyIncome(), user.getDeposit());

    return CreditDto.builder()
            .creditLimit(creditLimit)
            .creditResult(creditResult)
            .userCreditDto(userCreditDto)
            .build();
    }

    private int calculateAndSaveCreditScoreByUserIdentificationNumber(User user) {
        Random r = new Random();
        user.setCreditScore(r.nextInt());
        userEntityService.updateUser(user);
        return  user.getCreditScore();
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

        if(creditScore < 500) {
            returnValue = 0;
        }else if (creditScore >= 1000) {
            returnValue = (monthlyIncome.intValue() * creditLimitMultiplier) + deposit.divide(new BigDecimal(2), RoundingMode.DOWN).intValue();
        }else if (monthlyIncome.intValue() >= 10000) {
            returnValue = (monthlyIncome.intValue() * creditLimitMultiplier / 2) + deposit.divide(new BigDecimal(4), RoundingMode.DOWN).intValue();
        }else if(monthlyIncome.intValue() > 5000){
            returnValue = deposit.divide(new BigDecimal(5), RoundingMode.DOWN).intValue() + 20000;
        }else {
            returnValue = deposit.divide(new BigDecimal(10), RoundingMode.DOWN).intValue() + 10000;
        }

        return new BigDecimal(returnValue);
    }
}
