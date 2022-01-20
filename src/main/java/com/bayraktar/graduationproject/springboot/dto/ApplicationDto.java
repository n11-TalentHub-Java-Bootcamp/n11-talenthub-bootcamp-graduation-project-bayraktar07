package com.bayraktar.graduationproject.springboot.dto;

import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ApplicationDto implements Serializable {
    private final Long id;
    private final CreditResult creditResult;
    @Pattern(regexp = "^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
    private final LocalDate applicationDate;
    private final BigDecimal creditLimit;
}
