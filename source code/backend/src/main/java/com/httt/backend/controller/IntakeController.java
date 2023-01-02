package com.httt.backend.controller;

import com.httt.backend.entity.Intake;
import com.httt.backend.service.IntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class IntakeController {
    private IntakeService intakeService;

    @Autowired
    public IntakeController(IntakeService intakeService) {
        this.intakeService = intakeService;
    }

    @GetMapping(value = "/intakes")
    public List<Intake> getIntakeList() {
        return intakeService.findAll();
    }

}
