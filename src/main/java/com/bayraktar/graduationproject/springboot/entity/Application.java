package com.bayraktar.graduationproject.springboot.entity;

import com.bayraktar.graduationproject.springboot.entity.baseentity.BaseEntity;
import com.bayraktar.graduationproject.springboot.enums.CreditResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "application")
@Getter
@Setter
public class Application implements BaseEntity {

    @SequenceGenerator(name = "application_id_sequence", sequenceName = "application_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "application_id_sequence")
    @Id
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private CreditResult creditResult;
    private BigDecimal creditLimit;
    private LocalDate applicationDate;
}
