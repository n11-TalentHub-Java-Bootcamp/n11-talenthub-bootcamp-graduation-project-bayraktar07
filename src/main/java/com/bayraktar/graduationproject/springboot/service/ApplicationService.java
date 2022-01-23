package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import com.bayraktar.graduationproject.springboot.exception.BadRequestException;
import com.bayraktar.graduationproject.springboot.exception.NotFoundException;
import com.bayraktar.graduationproject.springboot.mapper.ApplicationMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.ApplicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationEntityService applicationEntityService;
    private final CreditService creditService;
    private final UserService userService;

    public ApplicationDto findByIdNumAndBirthDate(String identificationNumber, LocalDate birthDate) {
        UserDto userDto = userService.findUserByIdentificationNumber(identificationNumber);
        if(userDto.getBirthDate().isEqual(birthDate)) {
            Application application = applicationEntityService.findByUserId(userDto.getId())
                    .orElseThrow(() -> new NotFoundException("ApplicationService.findByIdNumAndBirthDate - user doesn't have an application"));
            return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
        }
        throw new NotFoundException("ApplicationService.findByIdNumAndBirthDate - given information doesn't match with identification number:" + userDto.getIdentificationNumber());
    }

    public ApplicationDto checkUserAndApplicationExistsAndSaveApplication(String identificationNumber) {
        UserDto user = userService.findUserByIdentificationNumber(identificationNumber);
        CreditDto creditInfo = creditService.getCreditResultByUser(user.getId());
        ApplicationDto applicationDto = getApplicationDto(user, creditInfo);
        applicationDto = doesUserHaveApplication(user.getId(), applicationDto);
        Application application = applicationEntityService.save(ApplicationMapper.INSTANCE.applicationDtoToApplication(applicationDto));
        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }

    private ApplicationDto getApplicationDto(UserDto user, CreditDto creditInfo) {
        return ApplicationDto.builder()
                .creditResult(creditInfo.getCreditResult())
                .applicationDate(LocalDate.now())
                .creditLimit(creditInfo.getCreditLimit())
                .userId(user.getId())
                .build();
    }


    private ApplicationDto doesUserHaveApplication(Long id, ApplicationDto applicationDto) {
        applicationEntityService.findByUserId(id).ifPresent((application) -> {
            ApplicationDto previousApp = ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
            if(previousApp.equals(applicationDto)) {
                throw new BadRequestException("The user has already made an application with the registered information.");
            }
            applicationDto.setId(previousApp.getId());
        });
        return applicationDto;
    }
}
