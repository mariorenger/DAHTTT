package com.httt.backend.service;

import com.httt.backend.repository.IntakeRepository;
import com.httt.backend.entity.Intake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntakeServiceImpl implements IntakeService {
    private IntakeRepository intakeRepository;

    @Autowired
    public IntakeServiceImpl(IntakeRepository intakeRepository) {
        this.intakeRepository = intakeRepository;
    }

    @Override
    public Intake findByCode(String code) {
        Optional<Intake> intakeOptional = intakeRepository.findByIntakeCode(code);
        return intakeOptional.get();
    }

    @Override
    public Optional<Intake> findById(Long id) {
        return intakeRepository.findById(id);
    }

    @Override
    public List<Intake> findAll() {
        return intakeRepository.findAll();
    }

}
