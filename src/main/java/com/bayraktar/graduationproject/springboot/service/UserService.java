package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserEntityService userEntityService;

    public List<UserCreditDto> findAllUsers() {
        List<UserCreditDto> userDtos = UserMapper.INSTANCE.userListToUserCreditDtoList(userEntityService.findAllUsers());
        log.info("UserService.findAllUsers");
        return userDtos;
    }

    public UserCreditDto findUserById(Long id) {
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userToUserCreditDto(userEntityService.findUserById(id));
        log.info("UserService.findUserById -> foundUser: " + userCreditDto);
        return userCreditDto;
    }

    public UserDto findUserByIdentificationNumber(String id) {
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(userEntityService.findUserByIdentificationNumber(id));
        log.info("UserService.findUserByIdentificationNumber -> foundUser: " + userDto);
        return userDto;
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userEntityService.saveUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        log.info("UserService.saveUser -> savedUser: " + user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userEntityService.updateUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        log.info("UserService.updateUser -> updatedUser: " + user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public int deleteUser(Long id) {
        int i = userEntityService.deleteUserById(id);
        log.info("UserService.deleteUser -> delete UserId: " + id);
        return i;
    }
}
