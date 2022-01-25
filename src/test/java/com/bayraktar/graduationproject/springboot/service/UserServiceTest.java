package com.bayraktar.graduationproject.springboot.service;

import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;
import com.bayraktar.graduationproject.springboot.service.dataprovider.UserDataProvider;
import com.bayraktar.graduationproject.springboot.service.entityservice.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindAllUsers() {

        List<UserDto> userDtoList = UserDataProvider.getAllUserList();
        List<User> users = UserDataProvider.userDtoListToUserList(userDtoList);
        when(userEntityService.findAllUsers()).thenReturn(users);

        List<UserDto> allUsers = userService.findAllUsers();

        assertArrayEquals(allUsers.toArray(), userDtoList.toArray());
    }

    @Test
    void findUserById() {
        UserDto userDto = UserDataProvider.getUserDto(1L);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        UserCreditDto userCreditDto = UserMapper.INSTANCE.userToUserCreditDto(user);
        when(userEntityService.findUserById(any())).thenReturn(user);

        UserCreditDto user1 = userService.findUserById(1L);

        assertEquals(user1, userCreditDto);
    }

    @Test
    void findUserByIdentificationNumber() {
        long id = 3L;
        UserDto userDto = UserDataProvider.getUserDto(id);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        when(userEntityService.findUserByIdentificationNumber("55012124" + (100 + id))).thenReturn(user);

        UserDto userByIdentificationNumber = userService.findUserByIdentificationNumber("55012124" + (100 + id));

        assertEquals(userDto, userByIdentificationNumber);
    }

    @Test
    void saveUser() {
        UserDto userDto = UserDataProvider.getUserDto(null);
        UserDto firstUser = UserDataProvider.getFirstUser();
        User user = UserMapper.INSTANCE.userDtoToUser(firstUser);
        when(userEntityService.saveUser(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDto savedUser = userService.saveUser(userDto);

        assertEquals(savedUser, firstUser);
        assertEquals(1L, savedUser.getId());
    }

    @Test
    void updateUser() {
        UserDto userDto = UserDataProvider.getUserDto(1L);
        UserDto updatedFirstUser = UserDataProvider.getUpdatedFirstUser();
        User user = UserMapper.INSTANCE.userDtoToUser(updatedFirstUser);
        when(userEntityService.updateUser(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDto updateUser = userService.updateUser(userDto);

        assertEquals(updateUser, updatedFirstUser);
    }

    @Test
    void deleteUser() {
        UserDto firstUser = UserDataProvider.getFirstUser();
        User user = UserMapper.INSTANCE.userDtoToUser(firstUser);
        when(userEntityService.deleteUserById(anyLong())).thenReturn(1);

        userService.deleteUser(1L);

        verify(userEntityService).deleteUserById(1L);

    }
}