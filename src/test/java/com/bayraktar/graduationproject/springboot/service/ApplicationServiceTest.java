package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.exception.BadRequestException;
import com.bayraktar.graduationproject.springboot.exception.NotFoundException;
import com.bayraktar.graduationproject.springboot.mapper.ApplicationMapper;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.dataprovider.ApplicationDataProvider;
import com.bayraktar.graduationproject.springboot.service.dataprovider.CreditDataProvider;
import com.bayraktar.graduationproject.springboot.service.dataprovider.UserDataProvider;
import com.bayraktar.graduationproject.springboot.service.entityservice.ApplicationEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private CreditService creditService;

    @Mock
    private UserService userService;

    @Mock
    private ApplicationEntityService applicationEntityService;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    void findByIdNumAndBirthDate() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        ApplicationDto approvedApplicationDto = ApplicationDataProvider.getApprovedApplicationDto();
        Application application = ApplicationMapper.INSTANCE.applicationDtoToApplication(approvedApplicationDto);
        Optional<Application> applicationOptional = Optional.of(application);
        when(userService.findUserByIdentificationNumber("55012121100")).thenReturn(UserDataProvider.getApprovedLevelFourUser());
        when(userService.findUserByIdentificationNumber("55012121132")).thenThrow(new NotFoundException("Not found test"));
        when(applicationEntityService.findByUserId(1L)).thenReturn(applicationOptional);

        ApplicationDto byIdNumAndBirthDate = applicationService.findByIdNumAndBirthDate("55012121100", birthDate);

        assertEquals(byIdNumAndBirthDate, approvedApplicationDto);
        assertThrows(NotFoundException.class, () -> applicationService.findByIdNumAndBirthDate("55012121100", birthDate.minusDays(5)));
        assertThrows(NotFoundException.class, () -> applicationService.findByIdNumAndBirthDate("55012121132", birthDate));
    }

    @Test
    void checkUserAndApplicationExistsThenSaveApplication() {
        String identificationNumber = "55012121100";
        String identificationNumber2 = "12312120500";
        String identificationNumber3 = "55012120500";
        ApplicationDto approvedApplicationDto = ApplicationDataProvider.getApprovedApplicationDto();
        ApplicationDto approvedApplicationDtoNew = ApplicationDataProvider.getApprovedApplicationDtoNew();
        Application application = ApplicationMapper.INSTANCE.applicationDtoToApplication(approvedApplicationDto);
        Application application1 = ApplicationMapper.INSTANCE.applicationDtoToApplication(approvedApplicationDtoNew);
        Optional<Application> applicationOptional2 = Optional.of(application1);

        lenient().when(userService.findUserByIdentificationNumber(identificationNumber3)).thenReturn(UserDataProvider.getApprovedLevelFourUserNew());
        lenient().when(userService.findUserByIdentificationNumber(identificationNumber2)).thenReturn(UserDataProvider.getApprovedLevelFourUser());
        lenient().when(userService.findUserByIdentificationNumber(identificationNumber)).thenThrow( new NotFoundException("Not found -- Apptest"));
        lenient().when(creditService.getCreditResultByUser(1L)).thenReturn(CreditDataProvider.getApprovedResultAbove1000());
        lenient().when(creditService.getCreditResultByUser(3L)).thenReturn(CreditDataProvider.getApprovedResultAbove1000New());
        lenient().when(applicationEntityService.findByUserId(1L)).thenReturn(Optional.empty());
        lenient().when(applicationEntityService.findByUserId(3L)).thenReturn(applicationOptional2);
        lenient().when(applicationEntityService.save(ArgumentMatchers.any(Application.class))).thenReturn(application);


        ApplicationDto savedApplicationDto = applicationService.checkUserAndApplicationExistsThenSaveApplication(identificationNumber2);

        assertEquals(savedApplicationDto, approvedApplicationDto);
        assertThrows(NotFoundException.class, () -> applicationService.checkUserAndApplicationExistsThenSaveApplication(identificationNumber));
        assertThrows(BadRequestException.class, () -> applicationService.checkUserAndApplicationExistsThenSaveApplication(identificationNumber3));
    }
}