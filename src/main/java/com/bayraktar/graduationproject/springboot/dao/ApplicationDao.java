package com.bayraktar.graduationproject.springboot.dao;

import com.bayraktar.graduationproject.springboot.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Long> {


    Application findApplicationByIdentificationNumberAndBirthDate(String identificationNumber, LocalDate birthDate);
}
