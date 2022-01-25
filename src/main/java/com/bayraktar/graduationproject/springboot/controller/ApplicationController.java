package com.bayraktar.graduationproject.springboot.controller;

import com.bayraktar.graduationproject.springboot.dto.ApplicationDto;
import com.bayraktar.graduationproject.springboot.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/applications")
@AllArgsConstructor
@CrossOrigin
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/identification-numbers/{identificationNumber}")
    public ResponseEntity<ApplicationDto> getApplicationByIdentificationNumberAndBirthDate(@PathVariable String identificationNumber, String birthDate) {
        ApplicationDto applicationDto = applicationService.findByIdNumAndBirthDate(identificationNumber, LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE));
        return ResponseEntity.ok(applicationDto);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> saveNewApplication(@RequestParam String identificationNumber) {
        ApplicationDto applicationDto = applicationService.checkUserAndApplicationExistsThenSaveApplication(identificationNumber);

        return ResponseEntity.ok(applicationDto);
    }
}
