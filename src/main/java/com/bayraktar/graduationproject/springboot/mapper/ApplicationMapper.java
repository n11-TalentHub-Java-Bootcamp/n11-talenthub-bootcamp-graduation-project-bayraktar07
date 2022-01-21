package com.bayraktar.graduationproject.springboot.mapper;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mapping(target = "user.id", source = "userId")
    Application applicationDtoToApplication(ApplicationDto applicationDto);

    @Mapping(target = "userId", source = "user.id")
    ApplicationDto applicationToApplicationDto(Application application);

}
