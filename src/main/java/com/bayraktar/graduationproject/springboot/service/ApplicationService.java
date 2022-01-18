package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import com.bayraktar.graduationproject.springboot.mapper.ApplicationMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.ApplicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationEntityService applicationEntityService;
    private final CreditService creditService;
    //TODO: SMS Service and methods

    public ApplicationDto findApplicationByIdentificationNumberAndBirthDate(String identificationNumber, LocalDate birthDate) {
        Application application = applicationEntityService.findApplicationByIdentificationNumberAndBirthDate(identificationNumber, birthDate);
        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }

    public ApplicationDto saveApplication(UserDto userDto) {
        CreditDto creditInfo = creditService.getCreditResultByUser(userDto);
        return ApplicationDto.builder()
                .userDto(userDto)
                .creditResult(creditInfo.getCreditResult())
                .applicationDate(LocalDate.now())
                .creditLimit(creditInfo.getCreditLimit())
                .build();
    }
}
