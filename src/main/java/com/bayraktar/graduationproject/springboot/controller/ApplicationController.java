package com.bayraktar.graduationproject.springboot.controller;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.dto.UserDto;
import com.bayraktar.graduationproject.springboot.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/applications")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/identification-numbers/{identificationNumber}")
    public ResponseEntity<ApplicationDto> getApplicationByIdentificationNumberAndBirthDate(@PathVariable String identificationNumber, String birthDate) {
        ApplicationDto applicationDto = applicationService.findApplicationByIdentificationNumberAndBirthDate(identificationNumber, LocalDate.parse(birthDate));
        return ResponseEntity.ok(applicationDto);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> saveNewApplication(@RequestBody UserDto userDto) {
        ApplicationDto applicationDto = applicationService.saveApplication(userDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(applicationDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(applicationDto);
    }
}
