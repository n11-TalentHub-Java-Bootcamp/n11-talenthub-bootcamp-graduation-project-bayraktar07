package com.bayraktar.graduationproject.springboot.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class UserDto implements Serializable {
    private final Long id;
    @Pattern(regexp = "\\d{11}" ,message = "Incorrect entry. Correct format: 12345678901.")
    private final String identificationNumber;
    @Pattern(regexp = "\\w+")
    private final String name;
    @Pattern(regexp = "\\w+")
    private final String surname;
    private BigDecimal monthlyIncome;
    @Pattern(regexp = "\\d{10}" ,message = "Incorrect entry. Correct format: 3336662244.")
    private final String phoneNumber;
    @Past
    private final LocalDate birthDate;
    private final BigDecimal deposit;
}
