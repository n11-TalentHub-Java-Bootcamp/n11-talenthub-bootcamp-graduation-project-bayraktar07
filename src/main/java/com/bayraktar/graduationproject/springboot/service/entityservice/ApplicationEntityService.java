package com.bayraktar.graduationproject.springboot.service.entityservice;

import com.bayraktar.graduationproject.springboot.dao.ApplicationDao;
import com.bayraktar.graduationproject.springboot.entity.Application;
import com.bayraktar.graduationproject.springboot.service.entityservice.baseentityservice.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationEntityService extends BaseEntityService<Application, ApplicationDao> {

    public ApplicationEntityService(ApplicationDao dao) {
        super(dao);
    }

    public Application save(Application application) {
        return getDao().save(application);
    }

    public Optional<Application> findByUserId(Long userId) {
        return getDao().findByUserId(userId);
    }
}
