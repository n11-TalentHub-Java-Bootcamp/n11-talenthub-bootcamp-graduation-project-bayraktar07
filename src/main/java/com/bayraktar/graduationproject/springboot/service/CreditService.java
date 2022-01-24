package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.enums.CreditLimitMultiplier;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
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

    log.info("CreditService.getCreditResultByUser -> creditResult: " + creditResult + ", creditLimit: " + creditLimit + ", userIdentificationNumber: " + userCreditDto.getIdentificationNumber());
    return CreditDto.builder()
            .creditLimit(creditLimit)
            .creditResult(creditResult)
            .userCreditDto(userCreditDto)
            .build();
    }

    private int calculateAndSaveCreditScoreByUserIdentificationNumber(User user) {
        user.setCreditScore(user.getMonthlyIncome().divide(BigDecimal.valueOf(10),RoundingMode.DOWN).intValue());
        userEntityService.updateUser(user);
        log.info("CreditService.calculateAndSaveCreditScoreByUserIdentificationNumber -> New User Credit Score assigned. Assumed Credit Score - Monthly Income divided by 10 - creditScore: " + user.getCreditScore() );
        return  user.getCreditScore();
    }

    private CreditResult calculateCreditResult(Integer creditScore){
        CreditResult result;
        if(creditScore < 500) {
            result = CreditResult.DENIED;
        }else {
            result = CreditResult.APPROVED;
        }
        log.info("CreditService.calculateCreditResult -> creditScore: " + creditScore + " -> result: " + result);
        return result;
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

        log.info("CreditService.calculateCreditLimit -> creditScore: " + creditScore + ", monthlyIncome: " + monthlyIncome + ", deposit: " + deposit + " -> returned limit: " + returnValue);
        return new BigDecimal(returnValue);
    }
}
