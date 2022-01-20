package com.bayraktar.graduationproject.springboot.dao;

import com.bayraktar.graduationproject.springboot.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Long> {

    Application findByUserId(Long id);
}
