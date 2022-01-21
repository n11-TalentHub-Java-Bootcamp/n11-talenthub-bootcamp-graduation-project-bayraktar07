package com.bayraktar.graduationproject.springboot.dao;

import com.bayraktar.graduationproject.springboot.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Long> {

    Optional<Application> findByUserId(Long id);
}
