package com.bayraktar.graduationproject.springboot.mapper;

import com.bayraktar.graduationproject.springboot.dto.UserCreditDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> allUsers);

    List<User> userDtoListToUserList(List<UserDto> userDtos);

    User userCreditDtoToUser(UserCreditDto userCreditDto);

    UserCreditDto userToUserCreditDto(User user);

    UserDto userCreditDtoToUserDto(UserCreditDto userCreditDto);

    UserCreditDto userDtoToUserCreditDto(UserDto userDto);
}
