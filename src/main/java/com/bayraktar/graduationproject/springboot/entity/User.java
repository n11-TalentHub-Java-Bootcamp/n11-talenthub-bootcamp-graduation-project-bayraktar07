package com.bayraktar.graduationproject.springboot.entity;

import com.bayraktar.graduationproject.springboot.entity.baseentity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(unique = true, nullable = false)
    private String identificationNumber;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private BigDecimal monthlyIncome;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDate birthDate;
    private BigDecimal deposit;
    private Integer creditScore;
}
