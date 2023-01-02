package com.httt.backend.dto;

import com.httt.backend.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSheet {
    private Long questionId;
    private List<Choice> choices;
    private Integer point;
}
