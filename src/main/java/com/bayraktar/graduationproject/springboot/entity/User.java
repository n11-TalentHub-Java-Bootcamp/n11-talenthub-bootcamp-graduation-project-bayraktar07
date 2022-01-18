package com.bayraktar.graduationproject.springboot.entity;

import com.bayraktar.graduationproject.springboot.entity.baseentity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User implements BaseEntity {

    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_id_sequence")
    @Id
    private Long id;

    private String identificationNumber;
    private String name;
    private String surname;
    private BigDecimal monthlyIncome;
    private String phoneNumber;
    private LocalDate birthDate;
    private BigDecimal deposit;
    private Integer creditScore;
}
