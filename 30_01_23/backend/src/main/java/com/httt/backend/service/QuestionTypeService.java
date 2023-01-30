package com.httt.backend.service;

import com.httt.backend.ultilities.EQTypeCode;
import com.httt.backend.entity.QuestionType;

import java.util.List;
import java.util.Optional;

public interface QuestionTypeService {
    Optional<QuestionType> getQuestionTypeById(Long id);

    Optional<QuestionType> getQuestionTypeByCode(EQTypeCode code);

    List<QuestionType> getQuestionTypeList();

    void saveQuestionType(QuestionType questionType);

    void delete(Long id);

    boolean existsById(Long id);
}
