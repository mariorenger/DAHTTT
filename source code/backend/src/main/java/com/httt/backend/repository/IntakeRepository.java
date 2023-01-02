package com.httt.backend.repository;

import com.httt.backend.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntakeRepository extends JpaRepository<Intake, Long> {
    Optional<Intake> findByIntakeCode(String intakeCode);


}
