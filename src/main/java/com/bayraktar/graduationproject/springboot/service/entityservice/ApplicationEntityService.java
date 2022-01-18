package com.bayraktar.graduationproject.springboot.service.entityservice;

import com.bayraktar.graduationproject.springboot.dao.ApplicationDao;
import com.bayraktar.graduationproject.springboot.entity.Application;
import com.bayraktar.graduationproject.springboot.service.entityservice.baseentityservice.BaseEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApplicationEntityService extends BaseEntityService<Application, ApplicationDao> {

    public ApplicationEntityService(ApplicationDao dao) {
        super(dao);
    }

    public Application findApplicationByIdentificationNumberAndBirthDate(String identificationNumber, LocalDate birthDate){
        return getDao().findApplicationByIdentificationNumberAndBirthDate(identificationNumber, birthDate);
    }
}
