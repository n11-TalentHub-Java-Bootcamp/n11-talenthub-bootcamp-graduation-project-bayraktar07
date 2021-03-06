package com.bayraktar.graduationproject.springboot.service.entityservice;

import com.bayraktar.graduationproject.springboot.dao.UserDao;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.exception.NotFoundException;
import com.bayraktar.graduationproject.springboot.service.entityservice.baseentityservice.BaseEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserEntityService extends BaseEntityService<User, UserDao> {

    public UserEntityService(UserDao dao) {
        super(dao);
    }

    public List<User> findAllUsers(){
    return getDao().findAll();
    }

    public User findUserById(Long id){
        return getDao().findById(id)
                .orElseThrow(() -> new NotFoundException("UserEntityService.findUserById method returned null. User with id:" + id + " not found."));
    }

    public User findUserByIdentificationNumber(String id) {
        return getDao().findByIdentificationNumber(id)
                .orElseThrow(() -> new NotFoundException("UserEntityService.findUserByIdentificationNumber method returned null. User with id:" + id + " not found."));
    }

    public Integer findCreditScoreById(Long id) {
        return findUserById(id).getCreditScore();
    }

    public User saveUser(User user){
        return getDao().save(user);
    }

    public User updateUser(User user){
        findUserById(user.getId());
        return getDao().save(user);
    }

    public int deleteUserById(Long id){
        return getDao().deleteUserById(id);
    }
}
