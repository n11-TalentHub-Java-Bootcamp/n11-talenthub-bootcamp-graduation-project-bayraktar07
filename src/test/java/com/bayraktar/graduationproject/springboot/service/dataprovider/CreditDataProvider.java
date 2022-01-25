package com.bayraktar.graduationproject.springboot.service.dataprovider;

import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.enums.CreditLimitMultiplier;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditDataProvider {


    public static CreditDto getApprovedResultAbove1000() {

        UserDto approvedLevelFourUser = UserDataProvider.getApprovedLevelFourUser();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(approvedLevelFourUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.APPROVED)
                .creditLimit(new BigDecimal(approvedLevelFourUser.getMonthlyIncome().longValue() * CreditLimitMultiplier.MULTIPLIER.getValue() + approvedLevelFourUser.getDeposit().longValue() / 2L))
                .userCreditDto(userCreditDto)
                .build();
    }
    public static CreditDto getApprovedResultAbove1000New() {

        UserDto approvedLevelFourUser = UserDataProvider.getApprovedLevelFourUserNew();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(approvedLevelFourUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.APPROVED)
                .creditLimit(new BigDecimal(approvedLevelFourUser.getMonthlyIncome().longValue() * CreditLimitMultiplier.MULTIPLIER.getValue() + approvedLevelFourUser.getDeposit().longValue() / 2L))
                .userCreditDto(userCreditDto)
                .build();
    }


    public static CreditDto getApprovedResultBetween500_100AndIncomeAbove10K() {

        UserDto approvedLevelThreeUser = UserDataProvider.getApprovedLevelThreeUser();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(approvedLevelThreeUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.APPROVED)
                .creditLimit(new BigDecimal((approvedLevelThreeUser.getMonthlyIncome().longValue() * CreditLimitMultiplier.MULTIPLIER.getValue() / 2) + approvedLevelThreeUser.getDeposit().longValue() / 4L))
                .userCreditDto(userCreditDto)
                .build();
    }

    public static CreditDto getApprovedResultBetween500_100AndIncomeBetween10K_5K() {

        UserDto approvedLevelTwoUser = UserDataProvider.getApprovedLevelTwoUser();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(approvedLevelTwoUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.APPROVED)
                .creditLimit(new BigDecimal(20000 + approvedLevelTwoUser.getDeposit().longValue() / 5L))
                .userCreditDto(userCreditDto)
                .build();
    }

    public static CreditDto getApprovedResultBetween500_100AndIncomeBelow5K() {

        UserDto approvedLevelOneUser = UserDataProvider.getApprovedLevelOneUser();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(approvedLevelOneUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.APPROVED)
                .creditLimit(new BigDecimal(10000 + approvedLevelOneUser.getDeposit().longValue() / 10L))
                .userCreditDto(userCreditDto)
                .build();
    }

    public static CreditDto getDeniedResultBelow500() {

        UserDto deniedUser = UserDataProvider.getDeniedUser();
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userDtoToUserCreditDto(deniedUser);
        calculateAndSetCreditScore(userCreditDto);
        return CreditDto.builder()
                .creditResult(CreditResult.DENIED)
                .creditLimit(new BigDecimal(0))
                .userCreditDto(userCreditDto)
                .build();
    }

    private static void calculateAndSetCreditScore(UserCreditDto userCreditDto) {
        String identificationNumber = userCreditDto.getIdentificationNumber();
        int creditScore = Integer.parseInt(identificationNumber.substring(identificationNumber.length()-5)) % 10000;
        userCreditDto.setCreditScore(creditScore);
    }
}
