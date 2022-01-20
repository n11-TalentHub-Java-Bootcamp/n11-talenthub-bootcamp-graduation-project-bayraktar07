package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.CreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
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
    //TODO: SMS Service and methods

    public ApplicationDto findByIdNumAndBirthDate(String identificationNumber, LocalDate birthDate) {
        UserDto userDto = userService.findUserByIdentificationNumber(identificationNumber);
        if(userDto.getBirthDate().isEqual(birthDate)) {
            Application application = applicationEntityService.findByUserId(userDto.getId());
            return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
        }
        throw new NotFoundException("ApplicationService.findByIdNumAndBirthDate - given information doesn't match with identification number:" + userDto.getIdentificationNumber());
    }

    public ApplicationDto checkUserExistsAndSaveApplication(UserDto userDto) {
        UserDto registeredUser = userService.findUserByIdentificationNumber(userDto.getIdentificationNumber());
        if(!registeredUser.equals(userDto)) {
            throw new NotFoundException("ApplicationService.checkUserExistsAndSaveApplication given information doesn't match with identification number: " + userDto.getIdentificationNumber());
        }
        CreditDto creditInfo = creditService.getCreditResultByUser(userDto);
        ApplicationDto applicationDto = ApplicationDto.builder()
                .userDto(registeredUser)
                .creditResult(creditInfo.getCreditResult())
                .applicationDate(LocalDate.now())
                .creditLimit(creditInfo.getCreditLimit())
                .build();
        Application application = applicationEntityService.save(ApplicationMapper.INSTANCE.applicationDtoToApplication(applicationDto));
        return ApplicationMapper.INSTANCE.applicationToApplicationDto(application);
    }
}
