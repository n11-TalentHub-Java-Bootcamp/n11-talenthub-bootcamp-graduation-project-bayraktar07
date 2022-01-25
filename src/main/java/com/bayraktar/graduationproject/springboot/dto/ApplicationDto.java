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
    private Long id;
    private final CreditResult creditResult;
    @Pattern(regexp = "^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
    private final LocalDate applicationDate;
    private final BigDecimal creditLimit;
    private final Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationDto that = (ApplicationDto) o;

        if (creditResult != that.creditResult) return false;
        if (!applicationDate.isEqual(that.applicationDate)) return false;
        if (!creditLimit.equals(that.creditLimit)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = creditResult != null ? creditResult.hashCode() : 0;
        result = 31 * result + (applicationDate != null ? applicationDate.hashCode() : 0);
        result = 31 * result + (creditLimit != null ? creditLimit.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
