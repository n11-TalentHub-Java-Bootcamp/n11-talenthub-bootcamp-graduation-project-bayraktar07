package com.bayraktar.graduationproject.springboot.dao;

import com.bayraktar.graduationproject.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    int deleteUserById(Long id);
}
