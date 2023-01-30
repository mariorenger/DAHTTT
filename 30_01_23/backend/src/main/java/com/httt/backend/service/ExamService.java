package com.httt.backend.service;

import com.httt.backend.dto.AnswerSheet;
import com.httt.backend.dto.ChoiceList;
import com.httt.backend.dto.ExamQuestionPoint;
import com.httt.backend.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    Exam saveExam(Exam exam);

    Page<Exam> findAll(Pageable pageable);

    void cancelExam(Long id);

    List<Exam> getAll();

    Optional<Exam> getExamById(Long id);

    Page<Exam> findAllByCreatedBy_Username(Pageable pageable, String username);

    List<ChoiceList> getChoiceList(List<AnswerSheet> userChoices, List<ExamQuestionPoint> examQuestionPoints);
}
