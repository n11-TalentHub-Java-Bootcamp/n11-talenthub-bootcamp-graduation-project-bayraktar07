package com.bayraktar.graduationproject.springboot.mapper;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);


    @Mapping(target = "user", source = "userDto")
    Application applicationDtoToApplication(ApplicationDto applicationDto);

    @Mapping(target = "userDto", source = "user")
    ApplicationDto applicationToApplicationDto(Application application);

}
