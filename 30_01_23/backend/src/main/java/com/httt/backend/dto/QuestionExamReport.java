package com.httt.backend.dto;

import com.httt.backend.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionExamReport {
    private Question question;
    private int correctTotal;

}
