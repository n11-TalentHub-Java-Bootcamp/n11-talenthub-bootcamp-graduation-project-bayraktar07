package com.bayraktar.graduationproject.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class UserDto implements Serializable {
    private final Long id;
    private final String identificationNumber;
    private final String name;
    private final String surname;
    private final BigDecimal monthlyIncome;
    private final String phoneNumber;
    private final LocalDate birthDate;
    private final BigDecimal deposit;
    private Integer creditScore;

}
