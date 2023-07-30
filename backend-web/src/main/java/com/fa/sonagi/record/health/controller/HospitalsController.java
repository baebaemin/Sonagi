package com.fa.sonagi.record.health.controller;

import com.fa.sonagi.record.health.dto.HospitalsPostDto;
import com.fa.sonagi.record.health.dto.HospitalsPutDto;
import com.fa.sonagi.record.health.service.HospitalsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hospitals")
@RestController
@RequiredArgsConstructor
public class HospitalsController {

  private final HospitalsService hospitalsService;

  @PostMapping
  public ResponseEntity<?> registHospitals(@RequestBody HospitalsPostDto hospitalsPostDto) {
    hospitalsService.registHospitals(hospitalsPostDto);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<?> updateHospitals(@RequestBody HospitalsPutDto hospitalsPutDto) {
    hospitalsService.updateHospitals(hospitalsPutDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{hospitalId}")
  public ResponseEntity<?> deleteHospitals(@PathVariable Long hospitalId) {
    hospitalsService.deleteHospitalsById(hospitalId);
    return ResponseEntity.ok().build();
  }
}
