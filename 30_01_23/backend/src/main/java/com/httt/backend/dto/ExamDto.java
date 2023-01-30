package com.httt.backend.dto;

import com.httt.backend.entity.Exam;
import lombok.Data;

import java.util.List;

@Data
public class ExamDto {
    private Exam exam;
    private List<ExamQuestionPoint> examQuestionPoints;
}
