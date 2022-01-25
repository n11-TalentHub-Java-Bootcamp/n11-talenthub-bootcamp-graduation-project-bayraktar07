package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.dataprovider.CreditDataProvider;
import com.bayraktar.graduationproject.springboot.service.dataprovider.UserDataProvider;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private CreditService creditService;

    @Test
    void getCreditResultByUser() {
        CreditDto approvedResultAbove1000 = CreditDataProvider.getApprovedResultAbove1000();
        CreditDto approvedResultBetween500_100AndIncomeAbove10K = CreditDataProvider.getApprovedResultBetween500_100AndIncomeAbove10K();
        CreditDto approvedResultBetween500_100AndIncomeBetween10K_5K = CreditDataProvider.getApprovedResultBetween500_100AndIncomeBetween10K_5K();
        CreditDto approvedResultBetween500_100AndIncomeBelow5K = CreditDataProvider.getApprovedResultBetween500_100AndIncomeBelow5K();
        CreditDto deniedResultBelow500 = CreditDataProvider.getDeniedResultBelow500();
        UserDto deniedUser = UserDataProvider.getDeniedUser();
        UserDto approvedLevelOneUser = UserDataProvider.getApprovedLevelOneUser();
        UserDto approvedLevelTwoUser = UserDataProvider.getApprovedLevelTwoUser();
        UserDto approvedLevelThreeUser = UserDataProvider.getApprovedLevelThreeUser();
        UserDto approvedLevelFourUser = UserDataProvider.getApprovedLevelFourUser();
        User user1 = UserMapper.INSTANCE.userDtoToUser(deniedUser);
        User user2 = UserMapper.INSTANCE.userDtoToUser(approvedLevelOneUser);
        User user3 = UserMapper.INSTANCE.userDtoToUser(approvedLevelTwoUser);
        User user4 = UserMapper.INSTANCE.userDtoToUser(approvedLevelThreeUser);
        User user5 = UserMapper.INSTANCE.userDtoToUser(approvedLevelFourUser);
        when(userEntityService.findUserById(1L)).thenReturn(user1);
        when(userEntityService.findUserById(2L)).thenReturn(user2);
        when(userEntityService.findUserById(3L)).thenReturn(user3);
        when(userEntityService.findUserById(4L)).thenReturn(user4);
        when(userEntityService.findUserById(5L)).thenReturn(user5);

        CreditDto creditResultByUser1 = creditService.getCreditResultByUser(1L);
        CreditDto creditResultByUser2 = creditService.getCreditResultByUser(2L);
        CreditDto creditResultByUser3 = creditService.getCreditResultByUser(3L);
        CreditDto creditResultByUser4 = creditService.getCreditResultByUser(4L);
        CreditDto creditResultByUser5 = creditService.getCreditResultByUser(5L);

        assertEquals(creditResultByUser1, deniedResultBelow500);
        assertEquals(creditResultByUser2, approvedResultBetween500_100AndIncomeBelow5K);
        assertEquals(creditResultByUser3, approvedResultBetween500_100AndIncomeBetween10K_5K);
        assertEquals(creditResultByUser4, approvedResultBetween500_100AndIncomeAbove10K);
        assertEquals(creditResultByUser5, approvedResultAbove1000);
    }
}