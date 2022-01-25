package com.bayraktar.graduationproject.springboot.service.dataprovider;

import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.entity.User;
import com.bayraktar.graduationproject.springboot.mapper.UserMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDataProvider {

    public static List<User> userDtoListToUserList (List<UserDto> users) {
        return UserMapper.INSTANCE.userDtoListToUserList(users);
    }

    public static List<UserDto> getAllUserList() {

        List<UserDto> userDtoList = new ArrayList<>();
        for(long i = 1; i < 13; i++) {

            userDtoList.add(getUserDto(i));
        }

        return userDtoList;
    }

    public static UserDto getUserDto (Long id) {
        long temp;
        temp = Objects.requireNonNullElse(id, 0L);
        return UserDto.builder()
                .id(id)
                .name("name" + id)
                .surname("surname" + id)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012124" + ( 100 + temp))
                .deposit(new BigDecimal(1000).add(new BigDecimal(temp)))
                .monthlyIncome(new BigDecimal(100).add(new BigDecimal(temp)))
                .build();
    }

    public static UserDto getFirstUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012124" + 100)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(100))
                .build();
    }

    public static UserDto getUpdatedFirstUser() {

        return UserDto.builder()
                .id(1L)
                .name("updatedName" + 0)
                .surname("updatedSurname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012124" + 100)
                .deposit(new BigDecimal(2000))
                .monthlyIncome(new BigDecimal(200))
                .build();
    }

    public static UserDto getDeniedUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012120" + 400)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(100))
                .build();
    }

    public static UserDto getApprovedLevelOneUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012120" + 500)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(4000))
                .build();
    }

    public static UserDto getApprovedLevelTwoUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012120" + 500)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(6000))
                .build();
    }

    public static UserDto getApprovedLevelThreeUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012120" + 500)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(10000))
                .build();
    }

    public static UserDto getApprovedLevelFourUser() {

        return UserDto.builder()
                .id(1L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012121" + 100)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(6000))
                .build();
    }

    public static UserDto getApprovedLevelFourUserNew() {

        return UserDto.builder()
                .id(3L)
                .name("name" + 0)
                .surname("surname" + 0)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("5557778822")
                .identificationNumber("55012121" + 100)
                .deposit(new BigDecimal(1000))
                .monthlyIncome(new BigDecimal(6000))
                .build();
    }
}
