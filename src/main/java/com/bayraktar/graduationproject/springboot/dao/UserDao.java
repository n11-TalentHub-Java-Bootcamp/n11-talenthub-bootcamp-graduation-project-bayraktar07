package com.bayraktar.graduationproject.springboot.dao;

import com.bayraktar.graduationproject.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    int deleteUserById(Long id);

    Optional<User> findByIdentificationNumber(String id);
}
