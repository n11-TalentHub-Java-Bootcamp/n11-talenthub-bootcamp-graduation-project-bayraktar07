package com.bayraktar.graduationproject.springboot.dto;

import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ApplicationDto implements Serializable {
    private final Long id;
    private final UserDto userDto;
    private final CreditResult creditResult;
    private final LocalDate applicationDate;
    private final BigDecimal creditLimit;
}
