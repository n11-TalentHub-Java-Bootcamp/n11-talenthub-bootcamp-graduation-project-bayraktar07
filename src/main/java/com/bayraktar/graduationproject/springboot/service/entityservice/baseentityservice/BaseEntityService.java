package com.bayraktar.graduationproject.springboot.service.entityservice.baseentityservice;

import com.bayraktar.graduationproject.springboot.entity.baseentity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E, Long>> {

    private D dao;
}
