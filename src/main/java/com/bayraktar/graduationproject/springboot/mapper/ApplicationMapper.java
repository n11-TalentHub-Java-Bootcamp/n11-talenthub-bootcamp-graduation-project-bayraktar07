package com.bayraktar.graduationproject.springboot.mapper;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application applicationDtoToApplication(ApplicationDto applicationDto);

    ApplicationDto applicationToApplicationDto(Application application);

}
