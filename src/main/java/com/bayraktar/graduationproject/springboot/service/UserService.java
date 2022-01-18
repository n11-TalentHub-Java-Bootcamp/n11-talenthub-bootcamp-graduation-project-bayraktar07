package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityService userEntityService;

    public List<UserDto> findAllUsers() {
        return UserMapper.INSTANCE.userListToUserDtoList(userEntityService.findAllUsers());
    }

    public UserDto findUserById(Long id) {
        return UserMapper.INSTANCE.userToUserDto(userEntityService.findUserById(id));
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userEntityService.saveUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userEntityService.updateUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public int deleteUser(Long id) {
        return userEntityService.deleteUserById(id);
    }
}
